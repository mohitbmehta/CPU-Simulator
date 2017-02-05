import java.io.*;
import java.util.Vector;

public class CPU 

{	
	public static byte Register0;
	public static byte Register1;
	public static boolean status;
	public static boolean overflow;
	public static boolean underflow;
	public static byte ProgramCounter;
	public static byte  InstructionRegister;
	public static int temp;
	final public static byte Max=(byte) 225;
	private static final byte LOAD0 = 0;
	//private static final byte LOAD1  ;
	//private static final byte ADD ;
	//private static final byte SUBTRACT ;
	//private static final byte Store0 ;
	//private static final byte Store1 ;
	
	public enum InstructionSet
	{
	
		LOAD0,
		LOAD1,
		ADD,
		SUBTRACT,
		STORE0,
		STORE1,
	};
	

	void ResetCPU()
	{
		temp=0;
		Register0=0;
		Register1=0;
		status=true;
		overflow=false;
		underflow=false;
		ProgramCounter=0;
	}
	
	void DoLoad0(final Vector<Byte> p_Program)
	{
		Register0 = p_Program.get(ProgramCounter);
		ProgramCounter++;
		
	}
	
	void DoLoad1(final Vector<Byte> p_Program)
	{
		Register1 = p_Program.get(ProgramCounter);
		ProgramCounter++;
		
	}
	
	void DoAdd ()
	{
		temp=Register0+Register1;
		if(temp > Max)
		{
			overflow = true;
			temp = Max;
		}
		Register0 = (byte) temp;
	}
	
	void DoSub ()
	{
		temp=Register0-Register1;
		if (temp < 0)
		{
			underflow = true;
			temp=0;
		}
		Register0=(byte) temp;
	}
	
	void DoStore0(Vector<Byte> p_Program)
	{
		Register0=p_Program.get(ProgramCounter);
		ProgramCounter++;
		
	}
	
	void DoStore1(Vector<Byte> p_Program)
	{
		Register1=p_Program.get(ProgramCounter);
		ProgramCounter++;
		

	}

	 
	void Execute (Vector<Byte> p_Program)
	{
		ResetCPU();
		if(p_Program.size() > Max )

		{
		System.out.println("Unble to Process Further");
		}
		else
		{
			while(ProgramCounter<p_Program.size())
			{
				InstructionRegister=p_Program.get(ProgramCounter);
				ProgramCounter++;
			
				switch(InstructionRegister)
				{
				case LOAD0:
					DoLoad0 (p_Program);
					break;
				case LOAD1:
					DoLoad1 (p_Program);
					break;
				case ADD:
					DoAdd ();
					break;
				
				case SUBTRACT:
					DoSub ();
					break;
				case Store0:
					DoStore0 (p_Program);
					break;
				case Store1:
					DoStore1(p_Program);
					break;
					return ;
				}
			}
		}
	
	}	
}


	