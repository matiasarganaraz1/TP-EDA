package gui;
import java.io.IOException;

//import game.Board;
//import game.Position;
//import parsing.ArgumentParsing;
//import parsing.BoardParser;
//import parsing.ParsingException;
//import AI.MiniMaxTree;


public class Program {
	public static void main(String[] args) {
		new Window(3,true,true);
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