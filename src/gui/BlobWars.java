package gui;
import java.io.IOException;

import ai.MiniMax;

import game.Board;
import game.Movement;
;
//import parsing.ArgumentParsing;
//import parsing.BoardParser;
//import parsing.ParsingException;
//import AI.MiniMaxTree;


public class BlobWars {
	public static void main(String[] args) {
//		
//		Board board = new Board();
//		MiniMax minimax = new MiniMax(3, true, false, 2, board);
//		Movement movement = minimax.getNextMove();
//		if(movement == null)
//			System.out.println("PASS");
//		else
//			System.out.println(movement);
//			
		
		new Window(1,true,false);
//		try{
//			
//			ArgumentParsing argParser = new ArgumentParsing(args);
//			if(argParser.isVisual()){
//				new Window(argParser.getLevel(), argParser.isPruned(), argParser.isTimed());
//			
//			}else{
//
//				BoardParser boardParser = new BoardParser(argParser.getFile());
//				Board board= boardParser.parseFile();
//				MiniMaxTree tree= new MiniMaxTree(argParser.getLevel(), board, argParser.isPruned(), argParser.isTimed(), 
//						argParser.isDOT(), argParser.getStartingPlayer());
//				Position pos=tree.getNextMove();
//				if(pos==null){
//					System.out.println("PASS");
//				}else{
//					System.out.println(pos.toString());
//				}
//			}
//		}catch(ParsingException p){
//			System.out.println("An error has ocurred");
//		}catch(IOException e){
//			System.out.println("There has been an error while opening a file");
//		}

	}
}