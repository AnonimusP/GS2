using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Mvc;
using Microsoft.AspNetCore.Mvc.Rendering;
using Microsoft.EntityFrameworkCore;
using WebApplication5.Models;

namespace WebApplication5.Controllers
{
    public class GamesSQLController : Controller
    {
        public gamesstoreContext _context;

        public GamesSQLController(gamesstoreContext context)
        {
            _context = context;
        }

        // GET: GamesSQL
        public async Task<IActionResult> Index(string searchStr)
        {
            var games = from g in _context.Game
                        select g;
            if (!String.IsNullOrEmpty(searchStr))
            {
                games = games.Where(s => s.Title.Contains(searchStr));
            }
            return View(await games.ToListAsync());
        }

        // GET: GamesSQL/Details/5
        public async Task<IActionResult> Details(int? id)
        {
            if (id == null)
            {
                return NotFound();
            }

            var games = await _context.Game
                .SingleOrDefaultAsync(m => m.Id == id);
            if (games == null)
            {
                return NotFound();
            }

            return View(games);
        }

        // GET: GamesSQL/Create
        public IActionResult Create()
        {
            return View();
        }

        // POST: GamesSQL/Create
        // To protect from overposting attacks, please enable the specific properties you want to bind to, for 
        // more details see http://go.microsoft.com/fwlink/?LinkId=317598.
        [HttpPost]
        [ValidateAntiForgeryToken]
        public async Task<IActionResult> Create([Bind("Id,Title,Genre,Platform,Price")] Games games)
        {
            if (ModelState.IsValid)
            {
                _context.Add(games);
                await _context.SaveChangesAsync();
                return RedirectToAction(nameof(Index));
            }
            return View(games);
        }

        // GET: GamesSQL/Edit/5
        public async Task<IActionResult> Edit(int? id)
        {
            if (id == null)
            {
                return NotFound();
            }

            var games = await _context.Game.SingleOrDefaultAsync(m => m.Id == id);
            if (games == null)
            {
                return NotFound();
            }
            return View(games);
        }

        // POST: GamesSQL/Edit/5
        // To protect from overposting attacks, please enable the specific properties you want to bind to, for 
        // more details see http://go.microsoft.com/fwlink/?LinkId=317598.
        [HttpPost]
        [ValidateAntiForgeryToken]
        public async Task<IActionResult> Edit(int id, [Bind("Id,Title,Genre,Platform,Price")] Games games)
        {
            if (id != games.Id)
            {
                return NotFound();
            }

            if (ModelState.IsValid)
            {
                try
                {
                    _context.Update(games);
                    await _context.SaveChangesAsync();
                }
                catch (DbUpdateConcurrencyException)
                {
                    if (!GamesExists(games.Id))
                    {
                        return NotFound();
                    }
                    else
                    {
                        throw;
                    }
                }
                return RedirectToAction(nameof(Index));
            }
            return View(games);
        }

        // GET: GamesSQL/Delete/5
        public async Task<IActionResult> Delete(int? id)
        {
            if (id == null)
            {
                return NotFound();
            }

            var games = await _context.Game
                .SingleOrDefaultAsync(m => m.Id == id);
            if (games == null)
            {
                return NotFound();
            }

            return View(games);
        }

        // POST: GamesSQL/Delete/5
        [HttpPost, ActionName("Delete")]
        [ValidateAntiForgeryToken]
        public async Task<IActionResult> DeleteConfirmed(int id)
        {
            var games = await _context.Game.SingleOrDefaultAsync(m => m.Id == id);
            _context.Game.Remove(games);
            await _context.SaveChangesAsync();
            return RedirectToAction(nameof(Index));
        }

        private bool GamesExists(int id)
        {
            return _context.Game.Any(e => e.Id == id);
        }
    }
}
