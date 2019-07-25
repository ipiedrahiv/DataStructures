#include <iostream>

using namespace std;

void printBoard(int b[9]) {
  char symbol[4] = " XO";
  cout << symbol[b[0]] << "|" << symbol[b[1]] << "|" << symbol[b[2]] << endl;
  cout << "-----" << endl;
  cout << symbol[b[3]] << "|" << symbol[b[4]] << "|" << symbol[b[5]] << endl;
  cout << "-----" << endl;
  cout << symbol[b[6]] << "|" << symbol[b[7]] << "|" << symbol[b[8]] << endl;
}

void play(int b[9], int p) {
  int player[2] = {1,2};
  for(int i = 0 ; i < 9 ; i++) {
    if(b[i] == 0) {
      b[i] = player[p];
      p = -1*p + 1;
      printBoard(b);
      play(b,p);
      b[i] = 0;
      p = -1*p + 1;
    }
  }
}

int main() {
  //Definimos el tablero
  int board[9] = {0,0,0,0,0,0,0,0,0};
  play(board, 0);

  return 0;
}
