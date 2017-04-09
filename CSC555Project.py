# -*- coding: utf-8 -*-
"""
Created on Wed Apr  5 15:58:30 2017

@author: Jedrik
"""


import tkinter as tk
from tkinter import *

# I need this to define a page but IDK what it means
class Page(tk.Frame):
    def __init__(self, *args, **kwargs):
        tk.Frame.__init__(self, *args, **kwargs)
    def show(self):
        self.lift()

# Title sign in or register
class titlePage(tk.Frame):
    def __init__(self, *args, **kwargs):
       Page.__init__(self, *args, **kwargs)
       label = tk.Label(self, text="Welcome: Sign in or register")
       label.pack(side="top", fill="both", expand=True)

# Registration page
class registration(tk.Frame):
    def __init__(self, *args, **kwargs):
       Page.__init__(self, *args, **kwargs)
       label = tk.Label(self, text="Welcome: Sign in or register")
       label.grid(row = 1, column = 1)
       
       
       # Username
       root = Tk()
       L1 = Label(root, text = "User Name")
       L1.grid(row = 2, column = 1)
       E1 = Entry(root, bd = 5)
       E1.grid(row = 2, column = 2)
       

       # Text that user will enter
       L3 = Label(root, text = "Enter the following text:")
       var = StringVar()
       
       L4 = Message(root, anchor = 'w', justify = 'left', 
                    width = 400, textvariable = var, relief = RAISED,
                    font = ("Consolas", 12, "bold"))
    
       var.set("All human beings are born free and equal in dignity and rights.")
               #" They are endowed with reason and conscience and should act towards"
               #" one another in a spirit of brotherhood.")
       L3.grid(row = 3, column = 1)
       L4.grid(row = 3, column = 2)
    
    
       # Free text
       L2 = Label(root, text = "Enter text here: ")
       L2.grid(row = 4, column = 1)
       text = Text(root, width = 50, height = 5, wrap = WORD, font = ("Consolas", 12))
    
       text.grid(row = 4, column = 2)


class MainView(tk.Frame):
    def __init__(self, *args, **kwargs):
        tk.Frame.__init__(self, *args, **kwargs)
        title = titlePage(self)
        signIn = registration(self)

        buttonframe = tk.Frame(self)
        container = tk.Frame(self)
        buttonframe.pack(side="top", fill="x", expand=False)
        container.pack(side="top", fill="both", expand=True)

        title.place(in_=container, x=0, y=0, relwidth=1, relheight=1)
        signIn.place(in_=container, x=0, y=0, relwidth=1, relheight=1)

        b1 = tk.Button(buttonframe, text="Register", command=signIn.lift)
        b2 = tk.Button(buttonframe, text="Home", command=title.lift)

        b1.pack(side="left")
        b2.pack(side="left")

        title.show()

if __name__ == "__main__":
    root = tk.Tk()
    main = MainView(root)
    main.pack(side="top", fill="both", expand=True)
    root.wm_geometry("400x400")
    root.mainloop()
    




"""
# Submit Button which will create profile
def helloCallBack():
    tkinter.messagebox.showinfo("Hello Python", "Hello World")

B = tk.Button(top, text = "Hello", command = helloCallBack)"""



#root.mainloop()



