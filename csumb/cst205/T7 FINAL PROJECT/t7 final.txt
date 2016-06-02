################################################
#####  T7 VULCAN ENTERPRISE FINAL PROJECT  #####
#####  DANIEL KUSHNER  #########################
#####  JOSHUA SHRADER  #########################
#####  VANESSA ULLOA   #########################
#####  TYPE IN BATTLESHIP() TO PLAY THE GAME  ##
################################################

from random import randint
#  VARIABLES FOR SOUNDS AND PICTURES
fileSloc = "V:\Google Drive\CST 205\T7 FINAL PROJECT"  ##### CHANGE THIS TO YOUR DIRECTORY SO YOU CAN PLAY THE GAME PROPERLY, DON'T FORGET A SLASH AT THE END. 

#  example
#  fileSloc = "/Users/jshrader85/Desktop"

#  Sounds
ManBattleStation = fileSloc +  "\sounds\manbattlestation.wav"
Explode = fileSloc + "\sounds\explode1.wav"
WinSound = fileSloc + "\sounds\smb_stage_clear.wav"
LoseSound = fileSloc + "\sounds\smb_mariodie.wav"
SPLASH = fileSloc + "\sounds\SPLASH.WAV"

#  Picture
BattleshipGameLOGO = fileSloc + "\pictures\Battleship-Game-LOGO.gif"
WinPic = fileSloc + "\pictures\You_Win.jpg"
LosePic = fileSloc + "\pictures\you_lose.jpg"
        
# random number for row from 0 to length of board (9) in this case
def random_row(board):
    return randint(0, len(board) - 1)

# random number for column from 0 to length of board (9) in this case
def random_col(board):
    return randint(0, len(board[0]) - 1)
    
def print_board(board):
  for row in board:
    printNow (" ".join(row))    
    
#  Function to show instructions
def Help():
  showInformation("Let's Play Battleship!")
  showInformation("Guess a Row and Col to try and sink 3 Battleships.\nYou have 20 attemps to find the Battleships!")   

#######################################################################################################

def BATTLESHIP():
  Help()
  board = []
  
  for x in range(10):
      board.append(["-"] * 10)

# creating the "map" of "-"'s for an ocean - 10x10
  #printNow ("Let's play Battleship!")
  soundMBS = makeSound(ManBattleStation)
  play(soundMBS)
  pic_Start = makePicture(BattleshipGameLOGO)
  show(pic_Start)
  print_board(board)

# 1st ship, size = 1x1
  ship_row = random_row(board)
  ship_col = random_col(board)

# 2nd ship, size = 1x2
  ship_row2 = random_row(board)
  if ship_row2 == ship_row: #Did not want a ship coordinate to fall onto a previous ship
    ship_row2 = random_row(board) #so I set it to generate a new number if it did
  ship_row2_1 = ship_row2 - 1 #2nd part of this ship, needs to be adjacent so I set - 1 (could be +1)
  if not ship_row2_1 >= 0: # if first part is 0, then ship_row2_1 would be off the "map",
    ship_row2_1 += 2 #so I added 2 to bring it to a positive number

  ship_col2 = random_col(board)#same things as above for the column code
  if ship_col2 == ship_col:
      ship_col2 = random_col(board)
  ship_col2_1 = ship_col2

# 3rd ship, size = 1x3
  ship_row3 = random_row(board)
  if ship_row3 == ship_row2 or ship_row3 == ship_row:
    ship_row3 = random_row(board)
  ship_row3_1 = ship_row3 - 1
  if not ship_row3_1 >= 0:
    ship_row3_1 += 2
  ship_row3_2 = ship_row3_1 - 1
  if not ship_row3_1 >= 0:
    ship_row3_1 += 3
 
  ship_col3 = random_col(board)#same things as above for the column code
  if ship_col3 == ship_col2 or ship_col3 == ship_col:
      ship_col3 = random_col(board)
  ship_col3_1 = ship_col3
  ship_col3_2 = ship_col3


  turn = 0
  ships_sunk = 0
  while True:
      try:
          #guess_row = int(raw_input("Guess Row: "))
          #guess_col = int(raw_input("Guess Col: "))
          guess_row = int(requestString("Guess Row: "))
          guess_col = int(requestString("Guess Col: "))
         # so my code here basically says if you hit the 1x1 ship it should turn it to an X,
         #and not be able to hit it again.
          if (guess_row == ship_row and guess_col == ship_col) and (board[guess_row][guess_col] != "X"):
              #printNow("\nCongratulations! You sunk a battleship!")
              #printNow("One more to go!\n")
              showInformation("\nCongratulations! You sunk a battleship!")
              showInformation("One more to go!\n")
              soundEX = makeSound(Explode)
              play(soundEX)
              board[guess_row][guess_col] = "X"
              print_board(board)
              ships_sunk += 1
              if ships_sunk == 3:
                  printNow("\n You got them all! Pokemon!")
                  sound_Win = makeSound(WinSound)
                  play(sound_Win)
                  pic_Win = makePicture(WinPic)
                  show(pic_Win)
                  break
        # here is the same but for the 1x2 ship, I had to turn them all to X to prevent user,
        # from hitting the same part of the "ship".
          elif ((guess_row == ship_row2 and guess_col == ship_col2) or \
          (guess_row == ship_row2_1 and guess_col == ship_col2_1)) and (board[guess_row][guess_col] != "X"):
              #printNow("\nCongratulations! You sunk a battleship!")
              #printNow("There are still more to go!\n")
              showInformation("\nCongratulations! You sunk a battleship!")
              showInformation("There are still more to go!\n")
              soundEX = makeSound(Explode)
              play(soundEX)
              board[guess_row][guess_col] = "X"
              board[ship_row2][ship_col2] = "X"
              board[ship_row2_1][ship_col2_1] = "X"
              print_board(board)
              ships_sunk += 1
              if ships_sunk == 3:
                  printNow("\n You got them all! Pokemon!")
                  sound_Win = makeSound(WinSound)
                  play(sound_Win)
                  pic_Win = makePicture(WinPic)
                  show(pic_Win)
                  break
        # here is ship 3, again all are turned to x
          elif ((guess_row == ship_row3 and guess_col == ship_col3) or \
          (guess_row == ship_row3_1 and guess_col == ship_col3_1) or \
          (guess_row == ship_row3_2 and guess_col == ship_col3_2)) and (board[guess_row][guess_col] != "X"):
              #printNow("\nCongratulations! You sunk a battleship!")
              #printNow("One more to go!\n")
              showInformation("\nCongratulations! You sunk a battleship!")
              showInformation("There are still more to go!\n")
              soundEX = makeSound(Explode)
              play(soundEX)
              board[guess_row][guess_col] = "X"
              board[ship_row3][ship_col3] = "X"
              board[ship_row3_1][ship_col3_1] = "X"
              board[ship_row3_2][ship_col3_2] = "X"
              print_board(board)
              ships_sunk += 1
              if ships_sunk == 3:
                  printNow("\n You got them all! Pokemon!")
                  sound_Win = makeSound(WinSound)
                  play(sound_Win)
                  pic_Win = makePicture(WinPic)
                  show(pic_Win)
                  break
          else:
              if (guess_row < 0 or guess_row > (len(board) -1 )) or (guess_col < 0 or guess_col > (len(board[0]) - 1)):
                  printNow("\nOops, that's not even in the ocean.")
              elif(board[guess_row][guess_col] == "X") or (board[guess_row][guess_col] == "O"):
                  printNow("\nYou guessed that one already.")
              elif type(guess_row) is not int or type(guess_col) is not int:
                  printNow("Type a number!")
              else:
                  printNow("\nYou missed my battleship!")
                  board[guess_row][guess_col] = "O"
                  soundSP = makeSound(SPLASH)
                  play(soundSP)
                  turn += 1
                  showInformation("You have " + str(20-turn) + " attempts left!")
                  print_board(board)
                  if turn == 20:
                    #printNow("Game Over")
                    showInformation("Game Over")
                    sound_Lose = makeSound(LoseSound)
                    play(sound_Lose)
                    pic_Lose = makePicture(LosePic)
                    show(pic_Lose)
                    break
      except ValueError:
          #printNow("Please type a number!")
          showInformation("Please type a number!")        
  