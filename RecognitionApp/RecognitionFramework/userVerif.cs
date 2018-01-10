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
    public partial class userVerif : UserControl
    {
        private static userVerif _instance;

        public static userVerif Instance
        {
            get
            {
                if (_instance == null)
                    _instance = new userVerif();
                return _instance;
            }
        }
        public userVerif()
        {
            InitializeComponent();
        }

        private void userVerif_Load(object sender, EventArgs e)
        {

        }
    }
}
