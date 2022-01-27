import java.util.ArrayList;
import java.util.Comparator;

public class Puzzle implements Comparable<Puzzle>
{
    int h ;
    int[][] grid= new int[3][3];

    public Puzzle(int[][] g )
    {
        // copy ; NO ALIASING
        for(int i=0; i<3; i++)
        {
            for(int j =0; j<3; j++)
            {
                grid[i][j] = g[i][j];
            }
        }


    }


    public void printBoard()
    {
        for(int[] e: grid)
        {
            for(int j : e)
            {
                System.out.print(j+" ");
            }
            System.out.println();
        }
    }
    public ArrayList<String> moves() // returns a list of moves possible from given configuration
    {
        ArrayList<String> m = new ArrayList<String>();
        Coords zero = findZero();
        if(zero.col>0)
        {
            m.add("r");
        }
        if(zero.col<2)
        {
            m.add("l");
        }
        if(zero.row<2)
        {
            m.add("u");
        }
        if(zero.col>0)
        {
            m.add("d");
        }

        return m ;
    }
    public Puzzle neighbor(String move) // returns a puzzle with one move made
    {
        Puzzle p =new Puzzle(this.grid);
        Coords zero = findZero();
        switch(move)
        {
            case "r":
                p.swap(zero.row,zero.col,zero.row,zero.col-1);
                break;
            case "l":
                p.swap(zero.row,zero.col,zero.row,zero.col+1);
                break;
            case "u":
                p.swap(zero.row,zero.col,zero.row+1,zero.col);
                break;
            case "d":
                p.swap(zero.row,zero.col,zero.row-1,zero.col);
                break;

        }



        return p ;// returns the puzzle with one move made
    }

    public void swap(int i1 , int j1 , int i2, int j2)
    {
        int dummy = this.grid[i1][j1];
        this.grid[i1][j1] = this.grid[i2][j2];
        this.grid[i2][j2] =dummy;

    }


    public void setH(Puzzle goal) // compute hueristic distance from this instance to goal
    {
        // going to use manhattan distance for this one
        int dist =0;

        for(int i =0; i<3; i++)
        {
            for(int j=0; j<3; j++)
            {
                dist+= calcDist(grid[i][j], new Coords(i,j) );
            }
        }

        this.h = dist ;
    }
    public int calcDist(int num , Coords numCoords) // takes the number and num coords // do not count 0
    {
        Coords real = new Coords();

        switch(num)
        {
            case 0 :
                return 0;

            case 1:
                real.setCoords(0,0);
                break;
            case 2:
                real.setCoords(0,1);
                break;
            case 3:
                real.setCoords(0,2);
                break;
            case 4:
                real.setCoords(1,0);
                break;
            case 5:
                real.setCoords(1,1);
                break;
            case 6:
                real.setCoords(1,2);
                break;
            case 7:
                real.setCoords(2,0);
                break;
            case 8:
                real.setCoords(2,1);
                break;


        }
        int manhattan = Math.abs(real.row-numCoords.row)+Math.abs(real.col-numCoords.col);

        return manhattan;
    }


    public Coords findZero() // returns the coords of 0
    {
        for(int i =0 ; i<3; i++)
        {
            for(int j=0; j<3; j++)
            {
                if(grid[i][j]==0)
                    return new Coords(i,j);
            }
        }

        return null;
    }

    @Override
    public int compareTo(Puzzle o) {
        return this.h - o.h;
    }


    class Coords
    {
        int row ; int col ;
        public Coords(int r , int c)
        {
            this.row = r;
            this.col = c;
        }
        public Coords()
        {
        this.col =0 ;
        this.row =0 ;
        }
        public void setCoords(int r , int c )
        {
            this.row = r ;
            this.col = c ;
        }


    }


}
