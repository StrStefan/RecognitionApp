using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Drawing;
using System.Data;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace RecognitionFramework
{
    public partial class userIdentif : UserControl
    {
        private static userIdentif _instance;

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
        }

        private void userIdentif_Load(object sender, EventArgs e)
        {

        }
    }
}
