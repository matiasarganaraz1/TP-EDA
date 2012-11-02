package parsing;

import java.io.File;

public class ArgumentParser {

	private String[] args;
	private boolean isVisual, isTimed, isPruned;
	private int level, player;
	private File file;
	
	public ArgumentParser (String[] string){
		args=string;
		parseArguments();
	}
	
	private void parseArguments(){
		int i=0;
		if(args.length==0){
			throw new ParsingException();
		}
		i=setGameMode(i);
		i=setAI(i);
		i=setOptionalArg(i);
		if(i!=args.length){
			throw new ParsingException();
		}
	}
	
	private int setGameMode(int index){
		if(args[index].equals("-visual")){
			if(args.length<3){
				throw new ParsingException();
			}
			isVisual=true;
			return index + 1;
		}
		if(args[index].equals("-file")){
			if(args.length<6){
				throw new ParsingException();
			}
			index++;
			String name = "./"+args[index];
			file = new File(name);
			index++;
			if(!args[index].equals("-player")){
				throw new ParsingException();
			}else{
				index++;
				player=Integer.valueOf(args[index]);
				if(!(player==1||player==2)){
					throw new ParsingException();
				}
				return index+1;
			}
		}
		throw new ParsingException();
	}
	
	private int setAI(int index){
		if(args[index].equals("-maxtime")){
			isTimed=true;
			index++;
			level=Integer.valueOf(args[index]);
			return index+1;
		}
		if(args[index].equals("-depth")){
			index++;
			level=Integer.valueOf(args[index]);
			return index +1;
			
		}
		throw new ParsingException();
	}
	
	private int setOptionalArg(int index){
		if(args.length == index+1){
			if(args[index].equals("-prune")){
				isPruned=true;
				return index+1;
			}
		}
		return index;
	}

	public boolean isVisual() {
		return isVisual;
	}

	public boolean isTimed() {
		return isTimed;
	}

	public int getLevel() {
		return level;
	}

	public boolean isPruned() {
		return isPruned;
	}

	public File getFile() {
		return file;
	}

	public int getStartingPlayer() {
		return player;
	}
}
