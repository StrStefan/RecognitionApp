using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Drawing;
using System.Data;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
using ZKFPEngXControl;
using System.IO;
using System.Threading;

namespace RecognitionFramework
{
    public partial class userAdd : UserControl
    {
        private static userAdd _instance;
        AutoResetEvent doneEvent = new AutoResetEvent(false);
        BackgroundWorker backgroundWorker1 = new BackgroundWorker();

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

            backgroundWorker1.DoWork += backgroundWorker1_DoWork;
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
        }

        private void textBox1_TextChanged_1(object sender, EventArgs e)
        {
            //peopleIN = textBox1.Text;
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

    }
}
