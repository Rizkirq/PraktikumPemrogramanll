import javax.swing.*;
import java.awt.*;
import java.util.List;

public class MainFrame {

    public static void main(String[] args) {
        //membuat frame utama
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("COntoh Konkurensi di Swing");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(400, 200);
            frame.setLayout(new BorderLayout());

            //Label untuk status
            JLabel statusLabel = new JLabel("Tekan tomnol untuk mulai tugas berat", JLabel.CENTER);

            //tombol untuk memulai proses
            JButton starButton = new JButton("Mulai");

            //Progress bar
            JProgressBar progressBar = new JProgressBar(0, 60);
            progressBar.setValue(0);
            progressBar.setStringPainted(true);

            //tambahkan komponen ke frame
            frame.add(statusLabel, BorderLayout.NORTH);
            frame.add(progressBar, BorderLayout.CENTER);
            frame.add(starButton, BorderLayout.SOUTH);
            
             //tombol aksi
            starButton.addActionListener(e -> {
               starButton.setEnabled(false); //nonaktifkan tombol saat proses berjalan
               statusLabel.setText("Proses berjalan...");

               //buat SwingWorker untuk menangani tugas berat
               SwingWorker<Void, Integer> worker = new SwingWorker<>() {
                @Override
                protected Void doInBackground() throws Exception {
                    //simulasi tugas berat
                    for(int i = 0; i <= 100; i++) {
                        Thread.sleep(50);//simulasi delay
                        publish(i); // Perbarui progress
                    }
                    return null;
                }

                @Override
                protected void process(List<Integer> chunks) {
                    // Perbarui progress bar
                    int latestProgrress = chunks.get(chunks.size() - 1);
                    progressBar.setValue(latestProgrress);
                }

                @Override
                protected void done() {
                    //aksi setelah tugas selesai
                    starButton.setEnabled(true);
                    statusLabel.setText("Proses selesai!");
                }

               };

               //jalankan SwingWorker
               worker.execute();
            });

            //tampilkan frame
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);


        });
    }
}
