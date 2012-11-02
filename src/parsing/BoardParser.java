package parsing;

import game.Blob;
import game.Board;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class BoardParser {

	private BufferedReader buffer;

	public BoardParser(File file) throws FileNotFoundException {
		buffer = new BufferedReader(new FileReader(file));
	}
	
	public Board parseFile() throws IOException{
		Blob [][] blobs= new Blob[Board.SIZE][Board.SIZE];
		int i=0;
		String line;
		char[] charLine;
		while ((line = buffer.readLine()) != null) {
			if (line.length() != Board.SIZE || i > Board.SIZE) {
				throw new ParsingException();
			}
			charLine = line.toCharArray();
			for (int j = 0; j < Board.SIZE; j++) {
				switch (charLine[j]) {
				case ' ':
					blobs[i][j]=Blob.EMPTY;
					break;
				case '1':
					blobs[i][j]=Blob.PLAYER1;
					break;
				case '2':
					blobs[i][j]=Blob.PLAYER2;
					break;
				case 'r':
					blobs[i][j]=Blob.ROCK;
					break;
				default:
					throw new ParsingException();
				}
			}
			i++;
		}
		
		if(i!=Board.SIZE){
			throw new ParsingException();
		}
		Board board=new Board(blobs);
		return board;
	}

}
