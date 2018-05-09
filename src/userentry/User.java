
package userentry;

import java.awt.Image;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author RIDDHI DUTTA
 */
public class User
{
    private String userID,name,addr1,addr2,addr3;
    private Date dt;
    private char gender, maritalStatus;
    private int stateNo,cityNo;
    private String phoneNo,email, remarks;
    private boolean hasCar, hasHome;
    private PicInfo picinfo ;
    private Image image;

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public User()
    {
        
    }
    
    public static String createFormattedRemarks(String remarks)
    {
        if(remarks.length()==0)
            return "(0";
        String s[] = remarks.split("\\n");
        StringBuilder output= new StringBuilder();
        output.append("("+s.length+","); //bracket marks start of remark.
        for(String st:s)
            output.append(st.length()+",");
        for(String st:s)
            output.append(st);
        return output.toString();
    }

    public PicInfo getPicinfo() {
        return picinfo;
    }

    public void setPicinfo(PicInfo picinfo) {
        this.picinfo = picinfo;
    }
    
    public static String getRemarksFromFormatted(String formattedRemarks)
    {
        if(formattedRemarks.equals("0"))
            return "";
        StringBuilder output = new StringBuilder();
        int n;
        int ind = formattedRemarks.indexOf(',');
        n = Integer.parseInt(formattedRemarks.substring(0,ind));
        int[] length= new int[n];
        int prev = ind;
        
        for(int i=0;i<n;i++)
        {
            ind = formattedRemarks.indexOf(',',prev+1);
            length[i]= Integer.parseInt(formattedRemarks.substring(prev+1,ind));
            prev = ind;
        }
        prev++;
        for(int i=0;i<n;i++)
        {
            String s = formattedRemarks.substring(prev,prev+length[i]);
            prev+=(length[i]);
            output.append("\n");
            output.append(s);
        }
        
        return output.toString().substring(1);
    }
    
    public void save(PrintWriter pw)
    {
        String formattedRemarks = createFormattedRemarks(remarks);
        String s="";
        if(picinfo==null)
            s="0,0";
        else
            s= picinfo.offset+","+picinfo.length;
        pw.println(userID+"|"+name+"|"+UserCRUDFrame.sdf.format(dt)+"|"+gender+"|"+maritalStatus+"|"+addr1+"|"+addr2+"|"+addr3+"|"+stateNo+"|"+cityNo+"|"+email+"|"+phoneNo+"|"+hasCar+"|"+hasHome+"|"+s+"|"+formattedRemarks);
    
    }
    public static User load(String record) //loads data from file to the current user
    {
        User user = new User();
        String fields,remarksField;
        int p;
        p = record.indexOf("|(");
        fields = record.substring(0,p);
        
        String[] slice = fields.split("\\|");
        
        
        remarksField = record.substring(p+2);
        remarksField = getRemarksFromFormatted(remarksField);
        
        user.setUserID(slice[0]);

        user.setName(slice[1]);
        try 
        {
            user.setDt((UserCRUDFrame.sdf).parse(slice[2]));
            
        } catch (ParseException ex) {
            System.out.println(slice[2]);
        }
        user.setGender(slice[3].charAt(0));
        user.setMaritalStatus(slice[4].charAt(0));
        user.setAddr1(slice[5]);
        user.setAddr2(slice[6]);
        user.setAddr3(slice[7]);
        
        
        user.setStateNo(Integer.parseInt(slice[8]));
        user.setCityNo(Integer.parseInt(slice[9]));
        user.setEmail(slice[10]);
        user.setPhoneNo(slice[11]);
        user.setHasCar(Boolean.parseBoolean(slice[12]));
        user.setHasHome(Boolean.parseBoolean(slice[13]));
        
        String pc[] = slice[14].split("\\,");
        int offset = Integer.parseInt(pc[0]);
        int length = Integer.parseInt(pc[1]);
        user.setPicinfo(new PicInfo(offset,length));
        
        user.setRemarks(remarksField);
        
        return user;
    }
    public String getUserID()
    {
        return userID;
    }

    public String getName()
    {
        return name;
    }

    public String getAddr1()
    {
        return addr1;
    }

    public String getAddr2()
    {
        return addr2;
    }

    public String getAddr3()
    {
        return addr3;
    }

    public Date getDt()
    {
        return dt;
    }

    public char getGender()
    {
        return gender;
    }

    public char getMaritalStatus()
    {
        return maritalStatus;
    }

    public int getStateNo()
    {
        return stateNo;
    }

    public int getCityNo()
    {
        return cityNo;
    }

    public String getPhoneNo()
    {
        return phoneNo;
    }

    public String getEmail()
    {
        return email;
    }

    public String getRemarks()
    {
        return remarks;
    }

    public boolean isHasCar()
    {
        
        return hasCar;
    }

    public boolean isHasHome()
    {
        return hasHome;
    }

    public void setUserID(String userID)
    {
        this.userID = userID;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public void setAddr1(String addr1)
    {
        this.addr1 = addr1;
    }

    public void setAddr2(String addr2)
    {
        this.addr2 = addr2;
    }

    public void setAddr3(String addr3)
    {
        this.addr3 = addr3;
    }

    public void setDt(Date dt)
    {
        this.dt = dt;
    }

    public void setGender(char gender)
    {
        this.gender = gender;
    }

    public void setMaritalStatus(char maritalStatus)
    {
        this.maritalStatus = maritalStatus;
    }

    public void setStateNo(int stateNo)
    {
        this.stateNo = stateNo;
    }

    public void setCityNo(int cityNo)
    {
        this.cityNo = cityNo;
    }


    public void setPhoneNo(String phoneNo)
    {
        this.phoneNo = phoneNo;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public void setRemarks(String remarks)
    {
        this.remarks = remarks;
    }

    public void setHasCar(boolean hasCar)
    {
        this.hasCar = hasCar;
    }

    public void setHasHome(boolean hasHome)
    {
        this.hasHome = hasHome;
    }
    
    
    public User(String userID, String name, String addr1, String addr2, String addr3, Date dt, char gender, char maritalStatus, int stateNo, int cityNo, String phoneNo, String email, String remarks, boolean hasCar, boolean hasPhone) 
    {
        this.userID = userID;
        this.name = name;
        this.addr1 = addr1;
        this.addr2 = addr2;
        this.addr3 = addr3;
        this.dt = dt;
        this.gender = gender;
        this.maritalStatus = maritalStatus;
        this.stateNo = stateNo;
        this.cityNo = cityNo;
        this.phoneNo = phoneNo;
        this.email = email;
        this.remarks = remarks;
        this.hasCar = hasCar;
        this.hasHome = hasPhone;
    }
}
