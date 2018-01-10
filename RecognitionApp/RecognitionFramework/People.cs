using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace RecognitionFramework
{
    class People
    {
        private String name;
        private String description;
        private List<String> fingerPrint;

        public string Name
        {
          get => name;
          set => name = value;
        }

        public string Description
        {
          get => description;
          set => description = value;
        }

        public List<string> FingerPrint
        {
          get => fingerPrint;
          set => fingerPrint = value;
        }
    }
}
