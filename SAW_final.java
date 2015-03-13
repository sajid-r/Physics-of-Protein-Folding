import java.awt.*;
import java.io.PrintWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;




public class SAW_final { 

    public static void main(String[] args) {
        int N = Integer.parseInt(args[0]);
        int steps_to_go = 5;
        int rms_count = 0;
        int steps_taken=0;
        double [] rms = new double [50];
        double final_rms;
        double rms_sum;
        //StdDraw.setXscale(0, N);
        //StdDraw.setYscale(0, N);

        // draw many self-avoiding random walks
                                    

                while(steps_to_go<100)

                {
                    steps_to_go += 5;
                    rms_count = 0; 
                    steps_taken+=0;
                    final_rms = 0;
                    rms_sum = 0;             
                   //System.out.println("\nRunning for "+steps_to_go);

                                    while (rms_count<50) 
                                    {
                                       steps_taken = 0;
                                       //StdDraw.clear();
                                       boolean[][] visited = new boolean[N][N];

                                       // starting position
                                       int x = N / 2;
                                       int y = N / 2;
                                       visited[x][y] = true;


                                       // make a random move as long as particle is not boxed in
                                       while (!visited[x-1][y] || !visited[x+1][y] || !visited[x][y-1] || !visited[x][y+1]) {
                                 

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
                                            steps_taken+=1;
                                            // draw
                                            //StdDraw.filledSquare(x + 0.5, y + 0.5, .45);
                                            //StdDraw.show(25);

                                            if (x <= 0 || x >= N-1 || y <= 0 || y >= N-1) break;    // hit outside boundary
                                        }
                                        //StdDraw.show(1000);
                                       // System.out.println("\nRunning for "+steps_to_go+". Status : "+steps_taken);
                                        if(steps_to_go==steps_taken)
                                        {
                                            
                                            rms[rms_count]=Math.sqrt((Math.pow(N/2-x,2))+(Math.pow(N/2-y,2)));
                                            rms_count+=1;
                                            //distance = Math.round(distance, 5);
                                            //String dist_result = String.format("%.4f", distance);
                                        }
                                    }
                                    //Calculating rms from rms array
                                    while(--rms_count>=0)
                                    {
                                        rms_sum+=rms[rms_count]*rms[rms_count];
                                    }
                                    final_rms = Math.sqrt(rms_sum/50);


                                    //Write To File
                                    double logStep = Math.log(steps_to_go);
                                    double logRMS = Math.log(final_rms);

                                                        try
                                                        {
                                                              String data = logStep+" "+logRMS+"\n";
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

