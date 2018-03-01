import subprocess as sp

sp.call('clear',shell=True)

def print_centered(s):
    terminal_width = int(sp.check_output(['stty', 'size']).split()[1])
    print s.center(terminal_width)

print_centered('N')

import curses

screen = curses.initscr()
#try:
curses.noecho()
curses.curs_set(0)
screen.keypad(1)
#finally:

screen.addstr("Press a key")

def print_bug(screen, x, y):
	screen.addstr(x, y,     "   _V_  ", curses.A_REVERSE)
	screen.addstr(x + 1, y, "  |___| ", curses.A_REVERSE)
	screen.addstr(x + 2, y, " |  |  |", curses.A_REVERSE)
	screen.addstr(x + 3, y, " \o | o/", curses.A_REVERSE)
	screen.addstr(x + 4, y, "  '-.-  ", curses.A_REVERSE) 

def clear_bug(screen, x, y):
	screen.addstr(x, y,     "        ")
	screen.addstr(x + 1, y, "        ")
	screen.addstr(x + 2, y, "        ")
	screen.addstr(x + 3, y, "        ")
	screen.addstr(x + 4, y, "        ") 

i = 1
x = 0
y = 0

print_bug(screen, x, y)

while i < 100:
	event = screen.getch()
	curses.curs_set(0)
	curses.echo()

	if event == curses.KEY_LEFT:
		clear_bug(screen, x, y)
		if y > 0 :
			y = y - 1
			print_bug(screen, x, y)

	elif event == curses.KEY_RIGHT:
		clear_bug(screen, x, y)
		y = y + 1
		print_bug(screen, x, y)
	elif event == curses.KEY_UP:
		clear_bug(screen, x, y)
		if x > 0 :
			x = x - 5
			y = y + 4
			print_bug(screen, x, y)
			clear_bug(screen, x, y)
			x = x + 5
			y = y + 4
			print_bug(screen, x, y)
	elif event == curses.KEY_DOWN:
		clear_bug(screen, x, y)
		x = x + 1
		print_bug(screen, x, y)
	elif event == 27:
		clear_bug(screen, x, y)
		screen.addstr(x, y, "Exiting the program")
		curses.echo()
		curses.endwin()
		from os import sys
		sys.exit(1)

	curses.noecho()
	i = i + 1

curses.endwin()


