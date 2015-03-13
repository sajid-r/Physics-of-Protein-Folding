import java.awt.*;
import java.io.PrintWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;
public class SAW_modified { 

    public static void main(String[] args) {
        int N = Integer.parseInt(args[0]);
	double distance = 0;
	int steps = 0;

        StdDraw.setXscale(0, N);
        StdDraw.setYscale(0, N);

        // draw many self-avoiding random walks
        while (true) 
        {
           StdDraw.clear();
           boolean[][] visited = new boolean[N][N];
           steps = 0;
           // starting position
           int x = N / 2;
           int y = N / 2;
           visited[x][y] = true;
           StdDraw.setPenRadius(0.015);
           Color BLACK = Color.BLACK;
           StdDraw.setPenColor(BLACK);



           // make a random move as long as particle is not boxed in
           while (!visited[x-1][y] || !visited[x+1][y] || !visited[x][y-1] || !visited[x][y+1]) 
           {
     

               // try until you find an available move
               while (true) 
               {
                   double r = Math.random();
                   if      (r < 0.25 && !visited[x-1][y]) { x--; break; }
                   else if (r < 0.50 && !visited[x+1][y]) { x++; break; }
                   else if (r < 0.75 && !visited[x][y-1]) { y--; break; }
                   else if (r < 1.00 && !visited[x][y+1]) { y++; break; }
                }
                visited[x][y] = true;
		            steps+=1;

                // draw
                StdDraw.filledSquare(x + 0.5, y + 0.5, .45);
                StdDraw.show(25);

                if (x <= 0 || x >= N-1 || y <= 0 || y >= N-1) break;    // hit outside boundary
            }

		distance = Math.sqrt((Math.pow(N/2-x,2))+(Math.pow(N/2-y,2)));
		//distance = Math.round(distance, 5);
		String dist_result = String.format("%.4f", distance);
             	Color RED = Color.RED;
             	StdDraw.setPenColor(RED);
             	String s = "Steps = " + steps + ". R = "+ dist_result;
		StdDraw.textLeft(0,N,s);
            	StdDraw.show(1000);
            
            try
		{
              		String data = steps+" "+dist_result+"\n";
             		 File file =new File("SAW_output.txt");
 
               		 if(!file.exists())
                	{
                   		file.createNewFile();
               		 }
 
        
        		FileWriter fileWritter = new FileWriter(file.getName(),true);
			BufferedWriter bufferWritter = new BufferedWriter(fileWritter);
			bufferWritter.write(data);
			bufferWritter.close();
 
		}
      	catch(IOException e)
	{
       			 e.printStackTrace();
      	}

        }
    }

}
