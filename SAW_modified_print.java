import java.awt.*;
import java.io.PrintWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;
public class SAW_modified_print { 

    public static void main(String[] args) 
{
        int N = Integer.parseInt(args[0]);
	double distance = 0;
	int steps_count = 0;
	int steps_to_go = 5;
	int i = 50;
	double [] rms = new double[500];
	int rms_count = 0;
  double sum_dist_sq = 0;
  double rmsValue=0;
        //StdDraw.setXscale(0, N);
        //StdDraw.setYscale(0, N);

        // draw many self-avoiding random walks
        
while(steps_to_go<50)
{
  sum_dist_sq = 0;
	rms_count=0;
  rmsValue=0;
	steps_to_go += 5; 
  System.out.println("\nRunning for "+steps_to_go);
	while (rms_count<50) 
        { 
	//StdDraw.clear();

           boolean[][] visited = new boolean[N][N];
           //steps = 0;
           // starting position
           int x = N / 2;
           int y = N / 2;
           visited[x][y] = true;
           //StdDraw.setPenRadius(0.015);
          // Color BLACK = Color.BLACK;
           //StdDraw.setPenColor(BLACK);



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
		            steps_count+=1;

                // draw
               // StdDraw.filledSquare(x + 0.5, y + 0.5, .45);
                //StdDraw.show(25);

                if (x <= 0 || x >= N-1 || y <= 0 || y >= N-1) break;    // hit outside boundary
            }

		distance = Math.sqrt((Math.pow(N/2-x,2))+(Math.pow(N/2-y,2)));
		//distance = Math.round(distance, 5);
		String dist_result = String.format("%.4f", distance);
             	//Color RED = Color.RED;
             	//StdDraw.setPenColor(RED);
             	//String s = "Steps = " + steps + ". R = "+ dist_result;
		//StdDraw.textLeft(0,N,s);
            	//StdDraw.show(1000);
            if(steps_count==steps_to_go)
	{
		rms[rms_count]=distance;
		rms_count+=1;
    System.out.println("\nSteps_count = "+steps_count+" steps_to_go = "+steps_to_go);
	}
            

        }
        int j=0;
        while(++j<50)
        {

          sum_dist_sq+=rms[j]*rms[j];
        }
        rmsValue=Math.sqrt(sum_dist_sq/50);
	

try
    {
                  String data = steps_to_go+" "+rmsValue+"\n";
                 File file =new File("SAW_output.txt");
 
                   if(!file.exists())
                  {
                      file.createNewFile();
                   }
 
        
            FileWriter fileWritter = new FileWriter(file.getName(),true);
      BufferedWriter bufferWritter = new BufferedWriter(fileWritter);
      bufferWritter.write(data);
      bufferWritter.close();
      i--;
 
    }
        catch(IOException e)
  {
             e.printStackTrace();
        }






}
    
	

}

}
