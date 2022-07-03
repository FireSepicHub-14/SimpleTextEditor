import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import java.time.chrono.ThaiBuddhistChronology;

class SampleRditor {

    public static void main(String args[]) {
        JTextArea sou = new JTextArea();
        sou.setFont(new Font("Dialog", Font.PLAIN, 12));
        sou.setTabSize(4);
        EditorMenu sephy = new EditorMenu(sou);
        EditFace ef = new EditFace(sou, sephy);
    }
}

class EditFace extends JFrame {

    EditFace(JTextArea sou, EditorMenu sephy) {

        super("てきとうにつくってみた");
        Container c = getContentPane();
        c.setLayout(new BorderLayout());

        c.add(sephy, BorderLayout.NORTH);
        JScrollPane sp = new JScrollPane(sou);
        c.add(sp, BorderLayout.CENTER);

        setSize(800, 600);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}

class EditorMenu extends JMenuBar {

    EditorMenu(JTextArea sou) {

        super();

        FileMenu m1 = new FileMenu(sou);
        EditMenu m2 = new EditMenu(sou);
        ViewMenu m3 = new ViewMenu(sou);
        HelpMenu m4 = new HelpMenu(sou);

        add(m1);
        add(m2);
        add(m3);
        add(m4);
    }
}

class FileMenu extends JMenu implements ActionListener {

    JTextArea sou;
    JMenuItem mi1;
    JMenuItem mi2;
    JMenuItem mi3;
    JMenuItem mi4;
    JMenuItem mi5;

    FileMenu(JTextArea sou) {

        super("ふぁいる");
        this.sou = sou;

        mi1 = new JMenuItem("新規作成");
        mi2 = new JMenuItem("開く");
        mi3 = new JMenuItem("上書き保存");
        mi4 = new JMenuItem("名前を付けて保存");
        mi5 = new JMenuItem("閉じる");

        mi1.addActionListener(this);
        mi2.addActionListener(this);
        mi3.addActionListener(this);
        mi4.addActionListener(this);
        mi5.addActionListener(this);

        add(mi1);
        add(mi2);
        add(mi3);
        add(mi4);
        add(mi5);
    }

    public void actionPerformed(ActionEvent e) {

        EditFileAccess ea = new EditFileAccess();

        Object o = e.getSource();

        if (o == mi1) {
            sou.setText("");
        } else if (o == mi2) {
            sou.setText("");
            efa.fileOpen(sou);
        } else if (o == mi3) {
            if (EditorStatus.FILENAME.equals("")) {
                efa.fileSave(sou);
            } else {
                efa.overWrite(sou);
            }
        } else if (o == mi4) {
            efa.fileSave(sou);
        } else if (o == mi5) {
            efa.System.exit(0);
        }
    }
}

class EditMenu extends JMenu implements ActionListener {

    JTextArea sou;

    JMenuItem mi1;
    JMenuItem mi2;
    JMenuItem mi3;
    JMenuItem mi4;

    EditMenu(JTextArea sou) {

        super("編集");
        this.sou = sou;

        mi1 = new JMenuItem("切り取り");
        mi2 = new JMenuItem("コピー");
        mi3 = new JMenuItem("ペースト");
        mi4 = new JMenuItem("すべて選択");

        mi1.addActionListener(this);
        mi2.addActionListener(this);
        mi3.addActionListener(this);
        mi4.addActionListener(this);

        add(mi1);
        add(mi2);
        add(mi3);
        add(mi4);
    }

    public void actionPerformed(ActionEvent e) {

        Object o = e.getSource();

        if (o == mi1) {
            sou.cut();
        } else if (o == mi2) {
            sou.copy();
        } else if (o == mi3) {
            sou.paste();
        } else if (o == mi4) {
            sou.selectAll();
        }
    }
}

class ViewMenu extends JMenu implements ActionListener {

    JTextArea sou;
    JMenuItem mi1;
    JMenuItem mi2;
    JMenuItem mi3;
    JMenuItem mi4;

    ViewMenu(JTextArea sou) {

        super("表示");
        this.sou = sou;

        mi1 = new JMenuItem("文字拡大");
        mi2 = new JMenuItem("文字縮小");
        mi3 = new JMenuItem("フォントサイズ指定");
        mi4 = new JMenuItem("タブサイズ指定");

        mi1.addActionListener(this);
        mi2.addActionListener(this);
        mi3.addActionListener(this);
        mi4.addActionListener(this);

        add(mi1);
        add(mi2);
        add(mi3);
        add(mi4);
    }

    public void actionPerformed(ActionEvent e) {

        Object o = e.getSource();

        if (o == mi1) {
            int size = sou.getFont().getSize();
            size++;
            String name = sou.getFont().getName();
            int style = sou.getFont().getStyle();
            sou.setFont(new Font(name, style, size));
        } else if (o == mi2) {
            int size = sou.getFont().getSize();
            size--;
            String name = sou.getFont().getName();
            int style = sou.getFont().getStyle();
            sou.setFont(new Font(name, style, size));
        } else if (o == mi3) {
            String sizeString = JOptionPane.showInputDialog("フォントサイズを入力");
            if (sizeString == null) {
                return;
            } else {
                int size = Integer.parseInt(sizeString);
                String name = sou.getFont().getName();
                int style = sou.getFont().getStyle();
                sou.setFont(new Font(name, style, size));
            }
        } else if (o == mi4) {
            String tabString = JOptionPane.showInputDialog(タブサイズを入力);
            if (tabString == null) {
                return;
            } else {
                int tab = Integer.parseInt(tabString);
                sou.setTabSize(tabs);
            }
        }
    }
}

class HelpMenu extends JMenu implements ActionListener {

    JTextArea sou;
    JMenuItem mi1;
    JMenuItem mi2;

    HelpMenu(JTextArea sou) {

        super("ヘルプ");
        this.sou = sou;

        mi1 = new JMenuItem("バージョン情報");

        mi1.addActionListener(this);

        add(mi1);
    }

    public void actionPerformed(ActionEvent e) {

        Object o = e.getSource();

        if (o == mi1) {
            JOptionPane.showMessageDialog((component) null,
                    "わからんなりに作ってみたテキストエディター\nVersion v1.0.0\nLICENSE GPL-3.0 license\n-------------------------------------------------\nWebSite https://www.sepic.ml\nGitHub https://github.com/FireSepicHub-14",
                    "バージョン情報", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}

class EditFileAccess {
    public void fileOpen(JTextArea sou) {
        JFileChooser fc = new JFileChooser();
        fc.showOpenDialog(null);

        // File f = fc.getSelectedFileと
        File f = fc.getSelectedFile();

        try {
            BufferedReader br = new BufferedReader(new FileReader(f));

            String s;
            while ((s = br.readLine()) != null) {
                sou.append(s + '\n');
            }
        }

        catch (IOException e) {
            return;
        }

        EditorStatus.FILENAME = f.getPath();
    }

    public void fileSave(JTextArea sou) {

        JFileChooser fc = new JFileChooser();
        fc.showSaveDialog(null);
        File f = fc.getSelectedFile();

        try {
            PrintWriter pw = new PrintWriter(new FileWriter(f, false));

            String s = sou.getText();
            String st[] = s.split("\n");
            int limit = st.length;
            for (int i = 0; i < limit; i++) {
                pw.println(st[i]);
            }

            pw.close();
        }

        catch (IOException e) {
            return;
        }

        EditorStatus.FILENAME = f.getPath();
    }

    public void overWrite(JTextArea sou) {

        File f = new File(EditorStatus.FILENAME);

        try {
            PrintWriter pw = new PrintWriter(new FileWriter(f, false));

            String s = sou.getText();
            String st[] = s.split("\n");
            int limit = st.length;
            for (int i = 0; i < limit; i++) {
                pw.println(st[i]);
            }

            pw.close();
        }

        catch (IOException e) {
            return;
        }
    }
}

class EditorStatus {
    static String FILENAME = "";
}