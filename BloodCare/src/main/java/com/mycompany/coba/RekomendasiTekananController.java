/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.coba;

import com.sun.istack.internal.logging.Logger;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.collections.ObservableList;
import static javax.swing.JOptionPane.showMessageDialog;

/**
 * FXML Controller class
 *
 * @author A S U S
 */
public class RekomendasiTekananController implements Initializable {

    @FXML
    private Label lblTekananDarah;
    
    @FXML
    private Label lblKategori;
    
    @FXML
    private TableView tvRekomendasiMakanan;
    
    @FXML
    private TableColumn<Makanan,String> colDaftarMakanan;
    
    private ObservableList<Makanan> listMakanan;

    /**
     * Initializes the controller class.
     */
//    private  Connection conn = null;
//    
//    @FXML
//    private void TekananDarahButton(ActionEvent event) throws SQLException, IOException{
//        System.out.println("masuk");
//    String url = "jdbc:sqlite:databaase.db";
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
    public void initData(int sistol,int diastol){
        colDaftarMakanan.setCellValueFactory(new PropertyValueFactory<Makanan,String>("namaMakanan"));
        lblTekananDarah.setText(sistol+"/"+diastol);
        String sql;
        if(sistol<=90 || diastol<=60){
            lblKategori.setText("Rendah");
            sql="SELECT nama_makanan FROM rekomendasi_makanan_tekanan WHERE kategori_tekanan='Rendah'";
        }
        else if(sistol>=140 || diastol>=100){
            lblKategori.setText("Tinggi");
            sql="SELECT nama_makanan FROM rekomendasi_makanan_tekanan WHERE kategori_tekanan='Tinggi'";
        }
        else{
            lblKategori.setText("Normal");
            sql="SELECT nama_makanan FROM rekomendasi_makanan_tekanan WHERE kategori_tekanan='Normal'";
        }
        listMakanan=FXCollections.observableArrayList();
        try{
            Connection con=DBUtil.connect();
            Statement stmt=con.createStatement();
            ResultSet rs=stmt.executeQuery(sql);
            while(rs.next()){
                String namaMakanan=rs.getString("nama_makanan");
                listMakanan.add(new Makanan(namaMakanan));
            }
            tvRekomendasiMakanan.setItems(listMakanan);
        }
        catch(SQLException e){
            showMessageDialog(null,e.getMessage());
        }
    }
//    private void KadarGulaDarahButton(ActionEvent event) throws IOException {
//         javafx.scene.Parent root=FXMLLoader.load(getClass().getResource("/fxml/KadarGulaDarah.fxml"));
//        Scene scene = new Scene(root);
//        scene.getStylesheets().add("/styles/Style.css");
//        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
//        window.setScene(scene);
//        window.show();
//    }
    /*@FXML
    private void test(MouseEvent event) {
        int a = Integer.parseInt(txtTekananDarah.getText());
        if (a < 80) {
            lbl_label.setText("Berikut ini berbagai jenis makanan untuk darah rendah yang sangat dianjurkan agar tekanan darah kembali normal:\n"
                    + "1. Kismis tidak hanya membantu mengurangi hipertensi, namun juga mampu mengobati tekanan darah rendah secara efektif. Kismis dianggap sangat efektif sebagai makanan untuk darah rendah. Oleh karena itu, pastikan kismis menjadi bagian dari menu makanan Anda.\n"
                    + "2. Akar licorice menjadi makanan untuk darah rendah berikutnya yang sangat dianjurkan. Selain dapat baik untuk tekanan darah rendah, makanan ini juga baik untuk penderita sindrom kelelahan kronis. Akar licorice juga membantu menurunkan massa lemak tubuh, namun cenderung menyebabkan retensi cairan.\n"
                    + "3. Minum air secukupnya dan minum jus buah sepanjang hari sangat bermanfaat untuk menjaga tekanan darah tetap normal. Hal tersebut dikarenakan, dengan mengonsumsi makanan untuk darah rendah tersebut akan menghindari dehidrasi tubuh yang dapat menyebabkan tekanan darah rendah.\n"
                    + "4. Makanan untuk darah rendah lainnya yaitu, biji bunga matahari dan segenggam labu asin. Anda dapat mengonsumsi makanan tersebut untuk meningkatkan asupan garam serta menstabilkan tekanan darah Anda. Hal tersebut dikarenakan banyaknya kandungan vitamin E yang terdapat di dalamnya.\n"
                    + "5. Kombinasi susu dan kacang almond dapat membantu mengendalikan tekanan darah rendah dengan bekerja pada kelenjar adrenal dalam tubuh Anda. Untuk hasil yang maksimal, Anda perlu merendam kurang lebih 4-5 kacang almond dan diamkan semalamam. Keesokan paginya, Anda kupas kulitnya dan tumbuk hingga halus. Kemudian, tambahkan bubuk almond pada secangkir susu hangat dan nikmati setiap pagi selama beberapa minggu untuk meningkatkan tekanan darah Anda.\n" );
//                    + "6. Wortel adalah salah satu pilihan makanan untuk darah rendah. Jus wortel ternyata dapat meningkatkan sirkulasi darah dan mengendalikan kadar tekanan darah Anda. Untuk hasil terbaik, Anda hanya perlu menambahkan 2 sendok makan madu ke dalam segelas jus wortel. Kemudian Anda minum dengan keadaan perut kosong. Agar tekanan darah dapat stabil dengan cepat, minumlah jus tersebut dua kali sehari pagi  dan sore hari.\n"
//                    + "7. Daun kemangi  juga dianggap sebagai makanan untuk darah rendah. Mengapa demikian? karena daun tersebut mengandung banyak magnesium, potasium, vitamin B5 dan vitamin C yang efektif untuk mengatur tekanan darah. Anda dapat meminum ekstrak daun kemangi yang dicampur madu setiap hari dengan keadaan perut kosong atau cara lainnya, dengan mengunyah 4 - 5 helai daun kemangi pada pagi hari setiap hari.\n"
//                    + "8. Jus lemon ternyata sangat efektif untuk mengobati tekanan darah rendah akibat dehidrasi. Hal tersebut diakrenakan kandungan antioksidan dalam lemon yang mampu mengendalikan sirkulasi darah dan menjaga kestabilan tekanan darah. Untuk mengonsumsinya, Anda bisa meminum segelas jus lemon yang dicampur dengan sedikit garam dan gula. Kombinasi tersebut sangat efektif untuk mengatasi tekanan darah rendah.\n"
//                    + "9. Garam juga merupakan salah satu makanan untuk darah rendah. Hal tersebut dikarenakan kandungan natrium yang ada di dalamnya yang mampu meningkatkan tekanan darah. Namun, penting bagi Anda untuk membatasi penggunaan garam karena kelebihan garam dapat  mengakibatkan beberapa kondisi medis berbahaya.\n"
//                    + "10. Bawang putih mengandung senyawa yang juga mampu menstabilkan tekanan darah serta bermanfaat bagi kesehatan tubuh. Untuk hasil yang maksimal,  Anda cukup memakan 2 siung bawang putih mentah pada malam hari atau 1 jam sebelum tidur. Cara lain mengonsumsi makanan untuk darah rendah tersebut adalah dengan menambahkannya beberapa siung bawang putih pada masakan Anda.\n"
//                    + "11. Minuman berkafein seperti kopi, teh, minuman bersoda, dan coklat panas mampu meningkatkan tekanan darah untuk sementara. hal tersebut dikarenakan, minuman berkafein mampu merangsang pelepasan hormon adrenalin atau menghalangi reaksi hormon yang dapat melebarkan arteri. jadi, jika Anda sering mengalami tekanan darah rendah, maka sebaiknya meminum secangkir kopi hitam di pagi hari.\n"
//                    + "12. Teh rosemary juga termasuk dalam makanan untuk darah rendah. Tanaman ini dikenal mungkin merupakan mampu mengatasi tekanan darah rendah. Untuk hasil yang maksimal, minumlah teh rosemary secara rutin.\n"
//                    + "13. Buah bit adalah sejenis umbi berwarna merah yang berkhasiat untuk meningkatkan tekanan darah. Buah ini memang belum terkenal di Indonesia, namun sebagian orang sudah menggunakannya untuk mengatasi tekanan darah rendah. Anda cukup meminum segelas jus buah bit setiap hari, maka dapat mencegah  gejala yang disebabkan oleh tekanan darah rendah.\n"
//                    + "14. Tumbuhan khas Korea yang satu ini juga termasuk salah satu makanan untuk darah rendah yang dianjurkan. Sejak dulu ginseng sudah sering digunakan untuk mengatasi tekanan darah yang tidak stabil, termasuk mengatasi tekanan darah rendah.");
        } else if (a >= 80 && a < 140) {
            lbl_label.setText("Darah Anda sedang dalam kondisi normal."
                    + ""
                    + " Untuk menjaga kestabilan darah Anda, kami menyarankan untuk mengkonsumsi makanan dan minuman sebagai berikut:\n"
                    + "1. Jus juga minim protein dan lemak, sehingga tubuh akan mencernanya dengan cepat. Jus hijau pun bisa menimbulkan masalah gula darah, terutama jika jus tersebut dibuat dari berbagai campuran buah yang manis. Untuk menyeimbangkannya, kamu bisa mengkonsumsi jus bersama kacang-kacang atau telur. Jika tak punya bnyak waktu, cobalah bereksperimen dengan bubuk protein./n"
                    + "2. Buah kering kaya akan serat serta sumber vitamin dan mineral. Namun, makanan ini bisa meningkatkan gula darah sangat cepat jika kita tidak cermat memerhatikan porsinya.\n"
                    + "3. Biji-bijian utuh sangatlah bernutrisi ketimbang makanan yang disuling. Namun, pastikan kita memerhatikan porsinya secara tepat. Jangan lupa pula menyandingkannya dengan sumber protein dan lemak. Kamu bisa menambahkan alpukat, selai kacang atau keju.\n"
                    + "4. Acai bowls memang terlihat cantik dan mengandung antioksidan tinggi. Namun, makanan tersebut cenderung mengandung banyak karbohidrat, terutama jika kita menambahkan banyak granola di atasnya. Sebagai penyeimbang, pastikan kita memasukkan sumber protein dan menjaga porsinya.\n"
                    + "5. Granola adalah makanan sehat klasik yang sebetulnya bisa menjadi \"bom gula\". Satu ons granola lebih sedikit daripada sereal lainnya. Oleh karena itu, banyak orang menambahkan sejumlah bahan makanan seperti madu, maple syrup, brown rice syrup, hingga buah kering.\n"
                    + "6. Buah adalah makanan sehat. Begitu pula oatmeal. Kombinasi keduanya dianggap akan menghasilkan makanan yang sangat sehat. Buah-buahan dan oatmeal mengandung karbohidrat tinggi sehingga sulit membuat kita tetap berenergi dan kenyang lebih lama. Cobalah tambahkan selai kacang di atasnya untuk menambahkan protein dan perhatikan porsinya agar tidak mengalami lonjakan gila darah.");
        } else {
            lbl_label.setText("Tekanan Darah saat ini sudah melebihi batas normal. "
                    + ""
                    + "Untuk itu sangat disarankan untuk mengkonsumsi makanan dan minuman sebagai berikut:\n"
                    + "1. Dalam buah pisang mengandungan kalium yang tinggi yang dapat membantu menyeimbangkan kadar natrium yang tinggi dalam tubuh para penderita hipertensi, sehingga dapat membantu Anda dalam menurunkan tekanan darah. Anda dapat mengonsumsi pisang secara langsung atau juga sebagai teman untuk makan sereal atau yogurt.\n"
                    + "2. Sayuran hijau seperti bayam, kale, lobak hijau, sawi, dan lainnya juga mengandung kalium tinggi. Terlebih lagi, sayuran hijau juga mengandung kalsium yang juga berguna sebagai makanan penurun darah tinggi. Setengah cangkir bayam matang mampu memberikan 12% dari kebutuhan kalsium harian orang dewasa.\n"
                    + "\n"
                    + "Oleh karena itu, penting bagi Anda untuk selalu mengonsumsi sayuran hijau setiap harinya sebagai makanan penurun darah tinggi maupun makanan mengontrol tekanan darah Anda.\n"
                    + "3. Salah satu makanan yang kaya akan kalsium adalah yogurt. Sekitar 170 gram yogurt mengandung 300 mg kalsium, hampir 1/3 dari kebutuhan kalsium Anda. Kandungan natrium dalam yogurt juga termasuk rendah, sehingga aman bagi Anda penderita hipertensi. Selain itu, probiotik dalam yogurt juga dapat menjadi makanan penurun darah tinggi seperti yang ditemukan pada beberapa penelitian.\n"
                    + "4. Susu skim mengandung kalsium tinggi yang dapat membantu Anda untuk menurunkan tekanan darah. Selain itu, susu skim mengandung lemak rendah yang juga diperlukan sebagai salah satu minuman dan makanan penurunan darah tinggi.\n"
                    + "5. Kentang merupakan salah satu bahan makanan yang mengandung kalium dan magnesium tinggi. Kedua mineral ini mampu menurunkan tekanan darah.\n" );
//                    + "6. Buah-buahan kelompok berry, terutama blueberry, mengandung senyawa flavonoid. Sebuah penelitian menunjukkan bahwa flavonoid ini dapat mencegah tekanan darah tinggi dan juga dapat membantu sebagai makan penurun darah tinggi. Anda dapat menambahkan blueberry, raspberry, dan strawberry pada yogurt atau sereal pagi Anda.\n"
//                    + "7. Beberapa penelitian menunjukkan bahwa minum jus bit dapat menurunkan tekanan darah tinggi. Salah satu penelitian yang membuktikan hal tersebut datang dari penelitian yang diterbitkan oleh Nutrition Journal tahun 2013. Penelitian ini berhasil membuktikan bahwa terjadi penurunan tekanan darah sistolik setelah enam jam mengonsumsi jus bit, terutama terjadi pada partisipan pria.\n"
//                    + "8. Oatmeal merupakan makanan yang mengandung natrium rendah, lemak rendah, dan serat tinggi, sehingga dapat membantu Anda sebagai makanan penurun darah tinggi. Sarapan dengan oatmeal adalah ide bagus sebagai makanan pertama Anda sebelum menjalani hari. Jika Anda merasa rasa oatmeal terlalu hambar, Anda bisa menambahkan buah segar atau sedikit madu.\n"
//                    + "9. Beberapa jenis ikan tertentu, seperti ikan trout, memiliki kandungan vitamin D yang dapat membantu menurunkan tekanan darah Anda. Manfaat ikan terhadap tekanan darah ini juga telah diteliti. Penelitian yang diterbitkan oleh Nutrition Journal menyebutkan bahwa makan ikan berlemak, seperti salmon, sebanyak tiga kali seminggu dikaitkan dengan penurunan tekanan darah diastolik selama lebih dari delapan minggu. Para peneliti mengatakan bahwa banyak penelitian sebelumnya telah menemukan bahwa asam lemak omega-3 yang terkandung di ikan memiliki efek penurun tekanan darah.\n"
//                    + "10. Tentu Anda tidak menyangka jika cokelat dapat menurunkan tekanan darah. Mengonsumsi cokelat yang kaya akan flavonoid (biasa ditemukan pada dark chocolate) ternyata dapat membantu Anda dalam penurunan tekanan darah tinggi. ");
        }
    }*/

    @FXML
    private void re(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/re.fxml"));
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/styles/Styles.css");
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }

    @FXML
    private void profil(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/profil_user.fxml"));
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/styles/Styles.css");
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }

    @FXML
    private void keluar(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/Scene.fxml"));
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/styles/Styles.css");
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }

    @FXML
    private void btnCekTekananDarahOnClick(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/TekananDarah.fxml"));
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/styles/Styles.css");
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }

    @FXML
    private void btnCekGulaDarahOnClick(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/GulaDarah.fxml"));
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/styles/Styles.css");
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }
      @FXML
    private void home(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/Dasboard.fxml"));
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/styles/Styles.css");
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }
}
