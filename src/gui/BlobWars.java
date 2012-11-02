package gui;

import java.io.IOException;
import parsing.*;
import ai.MiniMax;
import game.Board;
import game.Movement;

public class BlobWars {
	public static void main(String[] args) {
		new Window(1, true, false);
		try {

			ArgumentParser argParser = new ArgumentParser(args);
			if (argParser.isVisual()) {
				new Window(argParser.getLevel(), argParser.isPruned(),
						argParser.isTimed());

			} else {

				BoardParser boardParser = new BoardParser(argParser.getFile());
				Board board = boardParser.parseFile();
				MiniMax minimax = new MiniMax(argParser.getLevel(),
						argParser.isPruned(), argParser.isTimed(),
						argParser.getStartingPlayer(), board);
				Movement mov = minimax.getNextMove();
				if (mov == null) {
					System.out.println("PASS");
				} else {
					System.out.println(mov);
				}
			}
		} catch (IOException e) {
			System.out.println("There has been an error while opening a file");
		} catch (ParsingException p) {
			System.out.println("An error has ocurred");
		}
	}
}