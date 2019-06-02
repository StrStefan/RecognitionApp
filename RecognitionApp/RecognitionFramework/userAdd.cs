using System;
using System.ComponentModel;
using System.Windows.Forms;
using System.IO;
using System.Threading;
using System.Net;
using System.Web.Script.Serialization;

namespace RecognitionFramework
{
    public partial class userAdd : UserControl
    {
        private static userAdd _instance;
        AutoResetEvent doneEvent = new AutoResetEvent(false);
        BackgroundWorker backgroundWorker1 = new BackgroundWorker();
        BackgroundWorker backgroundSentJson = new BackgroundWorker();
        People newPerson = new People();

        public static userAdd Instance
        {
            get
            {
                if (_instance == null)
                    _instance = new userAdd();
                return _instance;
            }
        }

        public userAdd()
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

        private void userAdd_Load(object sender, EventArgs e)
        {

        }

        private void backgroundWorker1_DoWork(object sender, System.ComponentModel.DoWorkEventArgs e)
        {
            FPrintBehavior fingerPrint = new FPrintBehavior();
            string nr = (string)e.Argument;
            fingerPrint.Save_FingerPrint(nr);
            string path = string.Format("C:\\Users\\Stefan\\Desktop\\ClientAppfPrint\\{0}.jpg", nr);
            byte[] image = System.IO.File.ReadAllBytes(path);
            string image_inBase64 = Convert.ToBase64String(image);
            newPerson.FingerPrint[Int32.Parse(nr) - 1] = image_inBase64;
        }

        private void backgroundSentJson_DoWork(object sender, System.ComponentModel.DoWorkEventArgs e)
        {
            var httpWebRequest = (HttpWebRequest)WebRequest.Create("http://127.0.0.1:8090/person/savePerson");
            httpWebRequest.ContentType = "application/json";
            httpWebRequest.Method = "POST";

            using (var streamWriter = new StreamWriter(httpWebRequest.GetRequestStream()))
            {
                string json = new JavaScriptSerializer().Serialize(new
                {
                    description = newPerson.Description,
                    fPrint = newPerson.FingerPrint,
                    id = newPerson.Id,
                    name = newPerson.Name

                });

                streamWriter.Write(json);
            }

            var httpResponse = (HttpWebResponse)httpWebRequest.GetResponse();
            using (var streamReader = new StreamReader(httpResponse.GetResponseStream()))
            {
                var result = streamReader.ReadToEnd();
            }

        }

        private void textBox1_TextChanged_1(object sender, EventArgs e)
        {

        }

        private void textBox2_TextChanged(object sender, EventArgs e)
        {

        }

        private void textBox4_TextChanged(object sender, EventArgs e)
        {

        }

        private void textBox1_TextChanged(object sender, EventArgs e)
        {

        }

        private void label1_Click(object sender, EventArgs e)
        {

        }

        private void label3_Click(object sender, EventArgs e)
        {

        }

        private void label4_Click(object sender, EventArgs e)
        {

        }

        private void label5_Click(object sender, EventArgs e)
        {

        }

        private void checkBox3_CheckedChanged(object sender, EventArgs e)
        {

        }

        //FingerPrints Buttons***

        private void button2_Click(object sender, EventArgs e)
        {

            backgroundWorker1.RunWorkerAsync("1");
            doneEvent.WaitOne(5000);
        }

        private void button3_Click(object sender, EventArgs e)
        {
            backgroundWorker1.RunWorkerAsync("2");
            doneEvent.WaitOne(5000);
        }

        private void button4_Click(object sender, EventArgs e)
        {
            backgroundWorker1.RunWorkerAsync("3");
            doneEvent.WaitOne(5000);
        }

        private void button5_Click(object sender, EventArgs e)
        {
            backgroundWorker1.RunWorkerAsync("4");
            doneEvent.WaitOne(5000);
        }

        private void button6_Click(object sender, EventArgs e)
        {
            backgroundWorker1.RunWorkerAsync("5");
            doneEvent.WaitOne(5000);
        }

        private void button7_Click(object sender, EventArgs e)
        {
            backgroundWorker1.RunWorkerAsync("6");
            doneEvent.WaitOne(5000);
        }

        private void button8_Click(object sender, EventArgs e)
        {
            backgroundWorker1.RunWorkerAsync("7");
            doneEvent.WaitOne(5000);
        }

        private void button9_Click(object sender, EventArgs e)
        {
            backgroundWorker1.RunWorkerAsync("8");
            doneEvent.WaitOne(5000);
        }

        private void button10_Click(object sender, EventArgs e)
        {
            backgroundWorker1.RunWorkerAsync("9");
            doneEvent.WaitOne(5000);
        }

        private void button11_Click(object sender, EventArgs e)
        {
            backgroundWorker1.RunWorkerAsync("10");
            doneEvent.WaitOne(5000);
        }
        //***

        //Save button
        private void button1_Click(object sender, EventArgs e)
        {
            newPerson.Id = 1001; //Neimportant
            newPerson.Name = textBox1.Text;
            newPerson.Description = textBox2.Text;
            backgroundSentJson.RunWorkerAsync();
            backgroundWorker1.CancelAsync();
        }

        private void checkBox1_CheckedChanged(object sender, EventArgs e)
        {

        }

        private void checkBox4_CheckedChanged(object sender, EventArgs e)
        {

        }

        private void checkBox5_CheckedChanged(object sender, EventArgs e)
        {

        }

        private void checkBox6_CheckedChanged(object sender, EventArgs e)
        {

        }

        private void button12_Click(object sender, EventArgs e)
        {
            newPerson.ResetValues();
            textBox1.Clear();
            textBox2.Clear();
        }
    }
}
