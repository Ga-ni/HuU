import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

//database로부터 정해진 type안에서 랜덤하게 음식과 음식점 정보를 고름.
//menu는 거리순, 추천순, 가격순의 프라이어티
//menu = 1 : distance / menu =2 : score / menu = 3 : price
public class Select {
   private static int menu; // 1-d / 2-s / 3-p
   private static int[] TypeList = new int[4];
   public static int Type;
   public static int Food;
   public static int Desc;

   Select() {
   }

   Select(int[] type, int m)
   {
      Type = -1;
      Food = -1;
      Desc = -1;

      System.arraycopy(type, 0, TypeList, 0, 4);
      menu = m;
      System.out.println("select: " + TypeList[0]);
      this.main();
      
   }

   public static void main() {
      System.out.println("main이에오");
      try {
         Class.forName("org.gjt.mm.mysql.Driver");
         System.out.println("!!");
      } catch (ClassNotFoundException e) {
         System.out.println("!");
      }
      Connection con = null;

      //database연결

      String url = "jdbc:mysql://localhost:3306/termp?useUnicode=true&characterEncoding=UTF-8";// EUC_KR
      String user = "root";
      String pass = "12345";
      try {
         con = DriverManager.getConnection(url, user, pass);
         System.out.println("My-SQL !!");
      } catch (SQLException e) {
         System.out.println("My-SQL!");
      }
      System.out.println("수화!!!!!!!!!!11 "+ menu);
      Type = Tpri(menu, con); // 1dist 2score 3price
      System.out.println("결과" + Type);
      Food = Fpri(Type, menu, con);
      System.out.println("F결과" + Food);
      Desc = Dpri(Food, menu, con);
      System.out.println("D결과" + Desc);
   }

   //입력받은 여러 type안에서 랜덤으로 type을 골라줌
   static int Tpri(int m, Connection con) {
      PreparedStatement ps = null;
      ResultSet rs = null;
      int[] IDset = new int[4];
      double[] priSet = new double[100];
      int i = -1, j = 0, count = 0;
      int length = 2;

      if (m == 1) { // dist
         try {
            String sql = null;
            int k;
            sql = "select T_ID, dist from type where T_id in (";
            for (k = 0; k < length - 1; k++)
               sql += TypeList[k] + ", ";
            sql += TypeList[k] + ")order by dist";

            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
               i++;
               IDset[i] = rs.getInt(1);
               priSet[i] = rs.getDouble("dist");
               System.out.println(IDset[i] + " " + priSet[i]);

            }
         } catch (SQLException e) {
            e.printStackTrace();
         }

      } else if (m == 2) { // SCORE
         try {
            String sql = null;
            int k;
            sql = "select T_ID, SCORE from type where T_id in (";
            for (k = 0; k < length - 1; k++)
               sql += TypeList[k] + ", ";
            sql += TypeList[k] + ")order by SCORE";

            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
               i++;
               IDset[i] = rs.getInt(1);
               priSet[i] = rs.getDouble("SCORE");
               System.out.println(IDset[i] + " " + priSet[i]);

            }
         } catch (SQLException e) {
            e.printStackTrace();
         }
      } else // price
      {
         try {
            String sql = null;
            int k;
            sql = "select T_ID, price from type where T_id in (";
            for (k = 0; k < length - 1; k++)
               sql += TypeList[k] + ", ";
            sql += TypeList[k] + ")order by price";

            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
               i++;
               IDset[i] = rs.getInt(1);
               priSet[i] = rs.getDouble("price");
            
            }
         } catch (SQLException e) {
            e.printStackTrace();
         }
      }

      return SelectRand(priSet, IDset, m, i + 1);
   }
//정해진 type안에서 food를 골라줌
   static int Fpri(int t, int m, Connection con) {
      PreparedStatement ps = null;
      ResultSet rs = null;
      int[] IDset = new int[5];
      double[] priSet = new double[100];
      int i = -1, j = 0, count = 0;

      if (m == 1) { // dist
         try {
            String sql = "select F.F_ID, dist from Food as F where F.T_ID=" + t + " order by dist";

            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
               i++;
               IDset[i] = rs.getInt(1);
               priSet[i] = rs.getDouble("dist");
               
            }
         } catch (SQLException e) {
            e.printStackTrace();
         }

      } else if (m == 2) { // SCORE
         try {
            String sql = "select F.F_ID, SCORE from Food as F where F.T_ID=" + t + " order by SCORE desc";

            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
               i++;
               IDset[i] = rs.getInt(1);
               priSet[i] = rs.getDouble("SCORE");
               
            }
         } catch (SQLException e) {
            e.printStackTrace();
         }
      } else // price
      {
         try {
            String sql = "select F.F_ID, price from Food as F where F.T_ID=" + t + " order by price";

            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
               i++;
               IDset[i] = rs.getInt(1);
               priSet[i] = rs.getDouble("price");
            
            }
         } catch (SQLException e) {
            e.printStackTrace();
         }
      }

      return SelectRand(priSet, IDset, m, i + 1);
   }
   //정해진 food안에서 음식점 정보를 랜덤으로 골라줌
   static int Dpri(int f, int m, Connection con) {
      PreparedStatement ps = null;
      ResultSet rs = null;
      int[] IDset = new int[5];
      double[] priSet = new double[100];
      int i = -1, j = 0, count = 0;

      if (m == 1) { // dist
         try {
            String sql = "select D.ID, dist from description as d where D.F_ID=" + f + " order by dist";

            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
               i++;
               IDset[i] = rs.getInt(1);
               priSet[i] = rs.getDouble("dist");
               
            }
         } catch (SQLException e) {
            e.printStackTrace();
         }

      } else if (m == 2) { // SCORE
         try {
            String sql = "select D.ID, SCORE from description as d where D.F_ID=" + f + " order by SCORE desc";

            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
               i++;
               IDset[i] = rs.getInt(1);
               priSet[i] = rs.getDouble("SCORE");
               
            }
         } catch (SQLException e) {
            e.printStackTrace();
         }
      } else // price
      {
         try {
            String sql = "select D.ID, price from description as d where D.F_ID=" + f + " order by price";

            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
               i++;
               IDset[i] = rs.getInt(1);
               priSet[i] = rs.getDouble("price");
               
            }
         } catch (SQLException e) {
            e.printStackTrace();
         }
      }

      return SelectRand(priSet, IDset, m, i + 1);
   }

   // 어레이에서 랜덤으로 한개를 고르기
   // 프라이어티가 클수록 어레이에 많이 할당
   static int SelectRand(double priSet[], int IDset[], int m, int length) {
      int[] randSet = new int[500];
      int i, j, count = 0, select;
      // rand array 만들기
      double ranking;
      int num = 25, indx;
      j = 0;
      indx = 0;
      if (m != 2)
         ranking = 100000000000000000000000.0;
      else
         ranking = -1;
      
      for (i = 0; i < length;) {

         if (m != 2) {

            if (ranking < priSet[i])
               num -= 5;
         } else {
            if (ranking > priSet[i])
               num -= 5;
         }

         ranking = priSet[i];
         
         for (j = 0; j < num; j++, indx++) {
            randSet[indx] = IDset[i];
         }
         i++;
         
      }
      count = indx;
      
      for (j = 0; j < count; j++)
         System.out.print(randSet[j] + " ");

      select = (int) (Math.random() * count);

      return randSet[select];

   }

}