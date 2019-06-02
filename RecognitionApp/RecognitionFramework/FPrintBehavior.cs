using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.IO;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using ZKFPEngXControl;

namespace RecognitionFramework
{
    class FPrintBehavior
    {

        private ZKFPEngX fp = new ZKFPEngX();
        int ok = 0;

        private void fp_OnImageReceived(ref bool AImageValid)
        {
            //fp.ControlSensor(13, 1);
            //fp.ControlSensor(13, 0);
            object imgdata = new object();
            bool b = fp.GetFingerImage(ref imgdata);
        }

        private void fPrintInit()
        {
            fp.SensorIndex = 0;
            fp.FPEngineVersion = "9";
            fp.InitEngine();
        }

        private void fPrintEnd()
        {
            fp.EndEngine();
        }

        private void Fp_OnFingerTouching()
        {
            ok = 0;
        }

        private void Fp_OnFingerLeaving()
        {
            ok = 1;
        }

        public void Save_FingerPrint(string buttonNr)
        {
            fPrintInit();
            fp.OnImageReceived += new IZKFPEngXEvents_OnImageReceivedEventHandler(fp_OnImageReceived);
            fp.OnFingerTouching += Fp_OnFingerTouching;
            fp.OnFingerLeaving += Fp_OnFingerLeaving;

            while (ok == 0)
            {
                if ( string.IsNullOrEmpty(fp.GetTemplateAsString()) == false )
                {
                    if (fp.GetTemplateAsString().Length > 1 && fp.LastQuality > 50 )
                    {
                        string path = string.Format("C:\\Users\\Stefan\\Desktop\\ClientAppfPrint\\{0}.jpg", buttonNr);
                        fp.SaveJPG(path);

                        fPrintEnd();
                        ok = 1;

                    }
                }
            }
        }
    }
}

