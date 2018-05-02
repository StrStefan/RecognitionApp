using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace RecognitionFramework
{
    class People
    {
        private int id;
        private String name;
        private String description;
        private List<String> fingerPrint;

        public People()
        {
            Init();
        }
        public void ResetValues()
        {
            Init();
        }

        private void Init()
        {
            id = 0;
            name = "0";
            description = "0";
            this.FingerPrint = new List<String>(new string[10]);
            for (int i = 0; i <= 9; i++)
            {
                FingerPrint[i] = "iVBORw0KGgoAAAANSUhEUgAAAAEAAAABCAYAAAAfFcSJAAAADUlEQVR42mNk+M9QDwADhgGAWjR9awAAAABJRU5ErkJggg==";
            }
        }

        public int Id { get => id; set => id = value; }
        public string Name { get => name; set => name = value; }
        public string Description { get => description; set => description = value; }
        public List<string> FingerPrint { get => fingerPrint; set => fingerPrint = value; }
    }
}
