namespace RecognitionFramework
{
    partial class Form1
    {
        /// <summary>
        /// Required designer variable.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// Clean up any resources being used.
        /// </summary>
        /// <param name="disposing">true if managed resources should be disposed; otherwise, false.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Windows Form Designer generated code

        /// <summary>
        /// Required method for Designer support - do not modify
        /// the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent()
        {
            this.menuStrip1 = new System.Windows.Forms.MenuStrip();
            this.adaugareToolStripMenuItem = new System.Windows.Forms.ToolStripMenuItem();
            this.verificareToolStripMenuItem = new System.Windows.Forms.ToolStripMenuItem();
            this.editareToolStripMenuItem = new System.Windows.Forms.ToolStripMenuItem();
            this.identificareToolStripMenuItem = new System.Windows.Forms.ToolStripMenuItem();
            this.panel = new System.Windows.Forms.Panel();
            this.menuStrip1.SuspendLayout();
            this.SuspendLayout();
            // 
            // menuStrip1
            // 
            this.menuStrip1.AutoSize = false;
            this.menuStrip1.BackColor = System.Drawing.SystemColors.Control;
            this.menuStrip1.Items.AddRange(new System.Windows.Forms.ToolStripItem[] {
            this.adaugareToolStripMenuItem,
            this.verificareToolStripMenuItem,
            this.editareToolStripMenuItem,
            this.identificareToolStripMenuItem});
            this.menuStrip1.Location = new System.Drawing.Point(0, 0);
            this.menuStrip1.Name = "menuStrip1";
            this.menuStrip1.Padding = new System.Windows.Forms.Padding(88, 5, 88, 5);
            this.menuStrip1.Size = new System.Drawing.Size(584, 40);
            this.menuStrip1.TabIndex = 0;
            this.menuStrip1.Text = "menuStrip1";
            this.menuStrip1.ItemClicked += new System.Windows.Forms.ToolStripItemClickedEventHandler(this.menuStrip1_ItemClicked);
            // 
            // adaugareToolStripMenuItem
            // 
            this.adaugareToolStripMenuItem.AutoSize = false;
            this.adaugareToolStripMenuItem.BackColor = System.Drawing.Color.FromArgb(((int)(((byte)(192)))), ((int)(((byte)(192)))), ((int)(((byte)(255)))));
            this.adaugareToolStripMenuItem.Font = new System.Drawing.Font("Segoe UI", 10F);
            this.adaugareToolStripMenuItem.Margin = new System.Windows.Forms.Padding(1);
            this.adaugareToolStripMenuItem.Name = "adaugareToolStripMenuItem";
            this.adaugareToolStripMenuItem.Size = new System.Drawing.Size(100, 25);
            this.adaugareToolStripMenuItem.Text = "Adaugare";
            this.adaugareToolStripMenuItem.Click += new System.EventHandler(this.adaugareToolStripMenuItem_Click);
            // 
            // verificareToolStripMenuItem
            // 
            this.verificareToolStripMenuItem.AutoSize = false;
            this.verificareToolStripMenuItem.BackColor = System.Drawing.Color.FromArgb(((int)(((byte)(192)))), ((int)(((byte)(192)))), ((int)(((byte)(255)))));
            this.verificareToolStripMenuItem.Font = new System.Drawing.Font("Segoe UI", 10F);
            this.verificareToolStripMenuItem.Margin = new System.Windows.Forms.Padding(1);
            this.verificareToolStripMenuItem.Name = "verificareToolStripMenuItem";
            this.verificareToolStripMenuItem.Size = new System.Drawing.Size(100, 25);
            this.verificareToolStripMenuItem.Text = "Verificare";
            this.verificareToolStripMenuItem.Click += new System.EventHandler(this.verificareToolStripMenuItem_Click);
            // 
            // editareToolStripMenuItem
            // 
            this.editareToolStripMenuItem.AutoSize = false;
            this.editareToolStripMenuItem.BackColor = System.Drawing.Color.FromArgb(((int)(((byte)(192)))), ((int)(((byte)(192)))), ((int)(((byte)(255)))));
            this.editareToolStripMenuItem.Font = new System.Drawing.Font("Segoe UI", 10F);
            this.editareToolStripMenuItem.Margin = new System.Windows.Forms.Padding(1);
            this.editareToolStripMenuItem.Name = "editareToolStripMenuItem";
            this.editareToolStripMenuItem.Size = new System.Drawing.Size(100, 25);
            this.editareToolStripMenuItem.Text = "Editare";
            this.editareToolStripMenuItem.Click += new System.EventHandler(this.editareToolStripMenuItem_Click);
            // 
            // identificareToolStripMenuItem
            // 
            this.identificareToolStripMenuItem.AutoSize = false;
            this.identificareToolStripMenuItem.BackColor = System.Drawing.Color.FromArgb(((int)(((byte)(192)))), ((int)(((byte)(192)))), ((int)(((byte)(255)))));
            this.identificareToolStripMenuItem.Font = new System.Drawing.Font("Segoe UI", 10F);
            this.identificareToolStripMenuItem.Margin = new System.Windows.Forms.Padding(1);
            this.identificareToolStripMenuItem.Name = "identificareToolStripMenuItem";
            this.identificareToolStripMenuItem.Size = new System.Drawing.Size(100, 25);
            this.identificareToolStripMenuItem.Text = "Identificare";
            this.identificareToolStripMenuItem.Click += new System.EventHandler(this.identificareToolStripMenuItem_Click);
            // 
            // panel
            // 
            this.panel.Location = new System.Drawing.Point(0, 43);
            this.panel.Name = "panel";
            this.panel.Size = new System.Drawing.Size(584, 473);
            this.panel.TabIndex = 1;
            // 
            // Form1
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(584, 516);
            this.Controls.Add(this.panel);
            this.Controls.Add(this.menuStrip1);
            this.MainMenuStrip = this.menuStrip1;
            this.Name = "Form1";
            this.Text = "RecognitionApp";
            this.menuStrip1.ResumeLayout(false);
            this.menuStrip1.PerformLayout();
            this.ResumeLayout(false);

        }

        #endregion

        private System.Windows.Forms.MenuStrip menuStrip1;
        private System.Windows.Forms.ToolStripMenuItem adaugareToolStripMenuItem;
        private System.Windows.Forms.ToolStripMenuItem verificareToolStripMenuItem;
        private System.Windows.Forms.ToolStripMenuItem editareToolStripMenuItem;
        private System.Windows.Forms.ToolStripMenuItem identificareToolStripMenuItem;
        private System.Windows.Forms.Panel panel;
    }
}

