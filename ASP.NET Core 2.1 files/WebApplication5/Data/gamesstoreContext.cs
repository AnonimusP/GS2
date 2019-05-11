using System;
using Microsoft.EntityFrameworkCore;
using Microsoft.EntityFrameworkCore.Metadata;

namespace WebApplication5.Models
{
    public partial class gamesstoreContext : DbContext
    {
        public gamesstoreContext(DbContextOptions<gamesstoreContext> options)
        : base(options)
        { }
        public DbSet<Games> Game { get; set; }

        protected override void OnConfiguring(DbContextOptionsBuilder optionsBuilder)
        {
            if (!optionsBuilder.IsConfigured)
            {
                optionsBuilder.UseMySql("server=localhost;port=3306;database=gamesstore;uid=a;password=a");
            }
        }

        protected override void OnModelCreating(ModelBuilder modelBuilder)
        {
            modelBuilder.Entity<Games>(entity =>
            {
                entity.ToTable("games");

                entity.Property(e => e.Id).HasColumnType("int(11)");

                entity.Property(e => e.Genre)
                    .IsRequired()
                    .HasMaxLength(255);

                entity.Property(e => e.Platform)
                    .IsRequired()
                    .HasMaxLength(255);

                entity.Property(e => e.Price).HasColumnType("decimal(10,0)");

                entity.Property(e => e.Title)
                    .IsRequired()
                    .HasMaxLength(255);
            });
        }
    }
}
