using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
using ZKFPEngXControl;

namespace RecognitionFramework
{
    public partial class Form1 : Form
    {
        public Form1()
        {
            InitializeComponent();
        }

        private void verificareToolStripMenuItem_Click(object sender, EventArgs e)
        {
            if (!panel.Controls.Contains(userVerif.Instance))
            {
                panel.Controls.Add(userVerif.Instance);
                userVerif.Instance.Dock = DockStyle.Fill;
                userVerif.Instance.BringToFront();
            }
            else
                userVerif.Instance.BringToFront();
        }

        private void editareToolStripMenuItem_Click(object sender, EventArgs e)
        {
        }

        private void adaugareToolStripMenuItem_Click(object sender, EventArgs e)
        {
            if (!panel.Controls.Contains(userAdd.Instance))
            {
                panel.Controls.Add(userAdd.Instance);
                userAdd.Instance.Dock = DockStyle.Fill;
                userAdd.Instance.BringToFront();
            }
            else
                userAdd.Instance.BringToFront();
        }

        private void identificareToolStripMenuItem_Click(object sender, EventArgs e)
        {
            if (!panel.Controls.Contains(userIdentif.Instance))
            {
                panel.Controls.Add(userIdentif.Instance);
                userIdentif.Instance.Dock = DockStyle.Fill;
                userIdentif.Instance.BringToFront();
            }
            else
                userIdentif.Instance.BringToFront();
        }

        private void menuStrip1_ItemClicked(object sender, ToolStripItemClickedEventArgs e)
        {

        }
    }
}
