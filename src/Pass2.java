import java.math.BigInteger;
import java.util.Arrays;


public class Pass2 {

	public static String[] opcodes;
	static String[] ObjCode = new String[8];
	static int b,p,x,i,n,e;
	static String opcodeform1[]={"C4","C0","F4","C8","F0","F8"};
	static String opcodeform2[]={"90","B4","A0","9C","98","AC","A4","A8","94","B0","B8"};
	static String opcodeform3and4[]={"18","58","40","28","88","24","64","3C","30","34","38","48","00","68","50","70",
			"08","6C","74","04","D0","20","60","44","D8","4C","EC","0C","78","54","80","D4","14","7C","E8","84","10"
			,"1C","5C","E0","2C","DC"};

	public static int getIndex(int row,String part)
	{
		for (int i = 0; i < Pass1.form[row].length; i++) {
			if(Pass1.form[row].equals(part))
				return i;
		}
		return -1;
	}
	
	public static void getfullOP(String part)
	{
		int flag=0;
		if(part.endsWith(",x"))
		{
			ObjCode[3]="1";
			part = part.substring(0,part.indexOf(",x"));
		}
		else
			ObjCode[3]="0";
		if(part.startsWith("#"))
		{
			ObjCode[1]="0";
			ObjCode[2]="1";
		}
		else if(part.startsWith("@"))
		{
			ObjCode[1]="1";
			ObjCode[2]="0";
			flag = 1;
		} else
		{
			ObjCode[1]="0";
			ObjCode[2]="0";	
			flag = 2;
		}
		if(flag == 0 && Integer.parseInt(part.substring(1)) != Double.NaN)
			ObjCode[7] = Integer.toHexString(Integer.parseInt(part.substring(1))).toUpperCase();
		else if(flag == 0 && Integer.parseInt(part.substring(1)) == Double.NaN)
			
		else if(flag==1)
		{
			
		} else if(flag==2)
		{
			// value of the TA
			
			
		}
		
		
	}

	public static void toByteCode(Pass1 p)
	{
		int t=0;
		String[] parts = new String[5];
		opcodes = new String[p.lines.size()];
		for(int i=0;i<p.lines.size();i++)
		{
			parts = p.lines.get(i).split("\\s+");
			for(int j=0;j<parts.length;j++)
			{
				if(parts[j].charAt(0) == '+')
				{
					parts[j] = parts[j].substring(1);
					ObjCode[6]="1";
				}
				else
					ObjCode[6]="0";
				if(Arrays.asList(Pass1.form[0]).contains(parts[j]))
				{
					t = getIndex(0,parts[j]);
					ObjCode[0] = new BigInteger(opcodeform1[t], 16).toString(2);
				} else if(Arrays.asList(Pass1.form[1]).contains(parts[j]))
				{
					t = getIndex(1,parts[j]);
					ObjCode[0] = new BigInteger(opcodeform2[t], 16).toString(2);
				} else if(Arrays.asList(Pass1.form[2]).contains(parts[j]) || Arrays.asList(Pass1.form[2]).contains(parts[j]))
				{
					t = getIndex(2,parts[j]);
					ObjCode[0] = new BigInteger(opcodeform3and4[t], 16).toString(2);
				}
				ObjCode[0] = ObjCode[0].substring(0,6);
			}
		}
	}

}
