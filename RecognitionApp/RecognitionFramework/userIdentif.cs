using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Drawing;
using System.Data;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
using System.Threading;
using System.Net;
using System.IO;
using System.Web.Script.Serialization;

namespace RecognitionFramework
{
    public partial class userIdentif : UserControl
    {
        private static userIdentif _instance;
        AutoResetEvent doneEvent = new AutoResetEvent(false);
        BackgroundWorker backgroundWorker1 = new BackgroundWorker();
        BackgroundWorker backgroundSentJson = new BackgroundWorker();
        String unknownfPrint;
        People matchedPerson = new People();

        public static userIdentif Instance
        {
            get
            {
                if (_instance == null)
                    _instance = new userIdentif();
                return _instance;
            }
        }

        public userIdentif()
        {
            InitializeComponent();

            backgroundSentJson.DoWork += backgroundSentJson_DoWork;
            backgroundWorker1.DoWork += backgroundWorker1_DoWork;
            backgroundWorker1.WorkerSupportsCancellation = true;
            backgroundWorker1.DoWork += (sender, e) =>
            {
                try
                {
                    if (!e.Cancel)
                    {
                        // Do work
                    }
                }
                finally
                {
                    doneEvent.Set();
                }
            };

        }

        private void backgroundWorker1_DoWork(object sender, System.ComponentModel.DoWorkEventArgs e)
        {
            FPrintBehavior fingerPrint = new FPrintBehavior();
            string nr = (string)e.Argument;
            fingerPrint.Save_FingerPrint(nr);
            string path = string.Format("C:\\Users\\Stefan\\Desktop\\fPrint\\{0}.jpg", nr);
            byte[] image = System.IO.File.ReadAllBytes(path);
            string image_inBase64 = Convert.ToBase64String(image);
            unknownfPrint = image_inBase64;
        }

        private void backgroundSentJson_DoWork(object sender, System.ComponentModel.DoWorkEventArgs e)
        {
            var httpWebRequest = (HttpWebRequest)WebRequest.Create("http://127.0.0.1:8090/person/MatchedPerson");
            httpWebRequest.ContentType = "application/json";
            httpWebRequest.Method = "POST";

            using (var streamWriter = new StreamWriter(httpWebRequest.GetRequestStream()))
            {
                string json = new JavaScriptSerializer().Serialize(new
                {
                    fPrint = unknownfPrint
                });

                streamWriter.Write(json);
            }

            var httpResponse = (HttpWebResponse)httpWebRequest.GetResponse();
            using (var streamReader = new StreamReader(httpResponse.GetResponseStream()))
            {
                var result = streamReader.ReadToEnd();
                JavaScriptSerializer jss = new JavaScriptSerializer();
                dynamic dynamicResult = jss.DeserializeObject(result);
                matchedPerson.Name = dynamicResult["name"];
                matchedPerson.Id = dynamicResult["id"];
                matchedPerson.Description = dynamicResult["description"];
                matchedPerson.FingerPrint = dynamicResult["fPrint"];
            }

        }

        private void userIdentif_Load(object sender, EventArgs e)
        {

        }

        private void button1_Click(object sender, EventArgs e)
        {
            backgroundWorker1.RunWorkerAsync("Unidentify_Person");
            doneEvent.WaitOne(5000);
        }

        private void button2_Click(object sender, EventArgs e)
        {
            backgroundSentJson.RunWorkerAsync();
            textBox1.Text =matchedPerson.Name;
            textBox2.Text =matchedPerson.Description;
        }
    }
}
