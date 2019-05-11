using System;
using System.Collections.Generic;

namespace WebApplication5.Models
{
    public partial class Games
    {
        public int Id { get; set; }
        public string Title { get; set; }
        public string Genre { get; set; }
        public string Platform { get; set; }
        public decimal Price { get; set; }
        //public DateTime Modified { get; set; }
    }
}
