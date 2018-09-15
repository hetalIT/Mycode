import java.util.Arrays;
import java.util.Scanner;
public class ins_pr10 {
    public static void main(String[] args) {
        Scanner in=new Scanner(System.in);
        int pt[]=new int[64],key[]=new int[64],i,j;
        char p[]=new char[16],k[]=new char[16];
        String str="";
        System.out.println("enter plaintext in hexadecimal form");
        p=in.next().toCharArray();
        System.out.println("enter key in hexadecimal form");
        k=in.next().toCharArray();
        pt=binary(p);
        key=binary(k);
        //Initial Permutation
        int ip1[]={58,50,42,34,26,18,10,2,60,52,44,36,28,20,12,4,
                    62,54,46,38,30,22,14,6,64,56,48,40,32,24,16,8,
                    57,49,41,33,25,17,9,1,59,51,43,35,27,19,11,3,
                    61,53,45,37,29,21,13,5,63,55,47,39,31,23,15,7};
        int temppt[]=new int[64];
        for(i=0;i<64;i++)
        {
            temppt[i]=pt[ip1[i]-1];
        }
        //permuted choice 1
        int pc1[]={57,49,41,33,25,17,9,1,58,50,42,34,26,18,10,2,59,51,43,35,27,19,11,3,60,52,44,36,
                    63,55,47,39,31,23,15,7,62,54,46,38,30,22,14,6,61,53,45,37,29,21,13,5,28,20,12,4};
        int tempkey[]=new int[56];
        for(i=0;i<56;i++)
        {
            tempkey[i]=key[pc1[i]-1];
        }
        //16 rounds of des
        int roundshift[]={1,1,2,2,2,2,2,2,1,2,2,2,2,2,2,1};
        int pc2[]={14,17,11,24,1,5,3,28,15,6,21,10,23,19,12,4,26,8,16,7,27,20,13,2,41,52,31,37,47,55,30,40,
                    51,45,33,48,44,49,39,56,34,53,46,42,50,36,29,32};
        int expansion_table[]={32,1,2,3,4,5,4,5,6,7,8,9,8,9,10,11,12,13,12,13,14,15,16,17,16,17,18,19,20,21,
                                20,21,22,23,24,25,24,25,26,27,28,29,28,29,30,31,32,1};
        
        //8 s-boxes
        int s1[][]={{14,4,13,1,2,15,11,8,3,10,6,12,5,9,0,7},{0,15,7,4,14,2,13,1,10,6,12,11,9,5,3,8},
            {4,1,14,8,13,6,2,11,15,12,9,7,3,10,5,0},{15,12,8,2,4,9,1,7,5,11,3,14,10,0,6,13}};
        int s2[][]={{15,1,8,14,6,11,3,4,9,7,2,13,12,0,5,10},{3,13,4,7,15,2,8,14,12,0,1,10,6,9,11,5},
            {0,14,7,11,10,4,13,1,5,8,12,6,9,3,2,15},{13,8,10,1,3,15,4,2,11,6,7,12,0,5,14,9}};
        int s3[][]={{10,0,9,14,6,3,15,5,1,13,12,7,11,4,2,8},{13,7,0,9,3,4,6,10,2,8,5,14,12,11,15,1},
            {13,6,4,9,8,15,3,0,11,1,2,12,5,10,14,7},{1,10,13,0,6,9,8,7,4,15,14,3,11,5,2,12}};
        int s4[][]={{7,13,14,3,0,6,9,10,1,2,8,5,11,12,4,15},{13,8,11,5,6,15,0,3,4,7,2,12,1,10,14,9},
            {10,6,9,0,12,11,7,13,15,1,3,14,5,2,8,4},{3,15,0,6,10,1,13,8,9,4,5,11,12,7,2,14}};
        int s5[][]={{2,12,4,1,7,10,11,6,8,5,3,15,13,0,14,9},{14,11,2,12,4,7,13,1,5,0,15,10,3,9,8,6},
            {4,2,1,11,10,13,7,8,15,9,12,5,6,3,0,14},{11,8,12,7,1,14,2,13,6,15,0,9,10,4,5,3}};
        int s6[][]={{12,1,10,15,9,2,6,8,0,13,3,4,14,7,5,11},{10,15,4,2,7,12,9,5,6,1,13,14,0,11,3,8},
            {9,14,15,5,2,8,12,3,7,0,4,10,1,13,11,6},{4,3,2,12,9,5,15,10,11,14,1,7,6,0,8,13}};
        int s7[][]={{4,11,2,14,15,0,8,13,3,12,9,7,5,10,6,1},{13,0,11,7,4,9,1,10,14,3,5,12,2,15,8,6},
            {1,4,11,13,12,3,7,14,10,15,6,8,0,5,9,2},{6,11,13,8,1,4,10,7,9,5,0,15,14,2,3,12}};
        int s8[][]={{13,2,8,4,6,15,11,1,10,9,3,14,5,0,12,7},{1,15,13,8,10,3,7,4,12,5,6,11,0,14,9,2},
            {7,11,4,1,9,12,14,2,0,6,10,13,15,3,5,8},{2,1,14,7,4,10,8,13,15,12,9,0,3,5,6,11}};
        int permutation_table[]={16,7,20,21,29,12,28,17,1,15,23,26,5,18,31,10,2,8,24,14,32,27,3,9,19,13,30,6,22,11,4,25};
        int permutation_result[]=new int[32];
        int tempptright[]=new int[48];
        int tempkey2[]=new int[48],row,col,si=0,colitr;
        int exor_result[]=new int[48],sbox_result[]=new int[32],sboxitr=0,intersectvalue=0;
        String rowstr="",colstr="",decTobin="";
        int exor_result2[]=new int[32];
        for(i=0;i<16;i++)
        {
            //shift operation
            tempkey=shifting(tempkey,roundshift[i]);
            //pc2
            for(j=0;j<48;j++)
            {
                tempkey2[j]=tempkey[pc2[j]-1];
                //expansion phase
                tempptright[j]=temppt[expansion_table[j]-1+32];
                if((tempkey2[j]==0 && tempptright[j]==0) || (tempkey2[j]==1 && tempptright[j]==1))
                    exor_result[j]=0;
                else
                    exor_result[j]=1;
            }
            for(j=0;j<8;j++)
            {
                rowstr+=exor_result[si];
                rowstr+=exor_result[si+6-1];
                row=Integer.parseInt(rowstr,2);
                for(colitr=si+1;colitr<si+5;colitr++)
                {
                    colstr+=exor_result[colitr];
                }
                col=Integer.parseInt(colstr,2);
                switch(j+1)
                {
                    case 1:intersectvalue=s1[row][col];break;
                    case 2:intersectvalue=s2[row][col];break;
                    case 3:intersectvalue=s3[row][col];break;
                    case 4:intersectvalue=s4[row][col];break;
                    case 5:intersectvalue=s5[row][col];break;
                    case 6:intersectvalue=s6[row][col];break;
                    case 7:intersectvalue=s7[row][col];break;
                    case 8:intersectvalue=s8[row][col];break;
                }
                //4 bit representation of s-box values
                decTobin=String.format("%4s", Integer.toBinaryString(intersectvalue)).replace(" ", "0");
                for(int s=0;s<4;s++)
                {Integer.parseInt(rowstr,2);
                    sbox_result[sboxitr++]=Integer.parseInt(String.valueOf(decTobin.charAt(s)),2);
                }
                decTobin="";
                //System.out.println("row="+rowstr+" "+row+"col="+colstr+" "+col+"  "+intersectvalue+"  "+decTobin);
                si=si+6;rowstr="";colstr="";
            }
            si=0;sboxitr=0;
            //P-box Permutation
            for(int b=0;b<32;b++)
            {
                permutation_result[b]=sbox_result[permutation_table[b]-1];
            }
            //exor with left part
            for(int x=0;x<32;x++)
            {
                if((temppt[x]==0 && permutation_result[x]==0) || (temppt[x]==1 && permutation_result[x]==1))
                    exor_result2[x]=0;
                else exor_result2[x]=1;
            }
            //value assignment to next round
            for(int a1=0;a1<32;a1++)
            {
                temppt[a1]=temppt[a1+32];
            }
            for(int a2=32;a2<64;a2++)
            {
                temppt[a2]=exor_result2[a2-32];
            }
            
        }
        //reverse order of left and right part
        int tempholder[]=new int[32];
        for(int a1=0;a1<32;a1++)
        {
            tempholder[a1]=temppt[a1];
            temppt[a1]=temppt[a1+32];
            temppt[a1+32]=tempholder[a1];
        }
        
        //Inverse Initial Permutation
        int inverse_ip[]={40,8,48,16,56,24,64,32,39,7,47,15,55,23,63,31,38,6,46,14,54,22,62,30,
                            37,5,45,13,53,21,61,29,36,4,44,12,52,20,60,28,35,3,43,11,51,19,59,27,
                            34,2,42,10,50,18,58,26,33,1,41,9,49,17,57,25};
        int inverse_ip_result[]=new int[64];
        for(i=0;i<64;i++)
        {
            inverse_ip_result[i]=temppt[inverse_ip[i]-1];
        }
        //covert int array to string
        String output=Arrays.toString(inverse_ip_result).replaceAll("\\[|\\]|,|\\s", "");
        String ct="";
       
       for(i=0,j=0;i<16;i++,j=j+4)
       {
           ct+=Integer.toString(Integer.parseInt(output.substring(j,j+4),2), 16);
       }
       
        System.out.println("Your cipher text is "+ct);
        /*for(int h=0;h<64;h++){System.out.print(inverse_ip_result[h]);}
        System.out.println("");
        for(int h=0;h<56;h++){System.out.print(tempkey[h]);}
        System.out.println("");
        for(int h=0;h<32;h++){System.out.print(tempptright[h]);}*/
    }
    public static int[] binary(char p[])
    {   int i,q,r,c=0,j,incr=0;
        String str="";
        int pt[]=new int[64];
        for(i=0;i<16;i++)
        {
            q=Character.getNumericValue(p[i]);
            while(q!=0)
            {
                r=q%2;
                str+=r;
                q=q/2;
                c++;
            }
            for(j=c;j<4;j++){str+="0";}
            StringBuffer str2=new StringBuffer(str).reverse();
            c=0;
            str="";
            for(j=0;j<4;j++)
            {
                pt[incr++]=Character.getNumericValue(str2.charAt(j));
            }
        }
        return pt;
    }
    public static int[] shifting(int key[],int shift)
    {
        int i,j,temp;
        for(i=0;i<shift;i++)
        {   temp=key[0];
            for(j=0;j<27;j++)
            {
                key[j]=key[j+1];
            }
            key[j]=temp;
            temp=key[28];
            for(j=28;j<55;j++)
            {
                key[j]=key[j+1];
            }
            key[j]=temp;
        }
        return key;
    }
    
}
