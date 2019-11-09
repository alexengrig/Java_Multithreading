package dev.alexengrig.competition.swing;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.concurrent.ExecutionException;

class MainFrame extends JFrame {
    private JLabel countLabel = new JLabel("-");
    private JLabel statusLabel = new JLabel("Task not completed.");
    private JButton startButton = new JButton("Start");
    private boolean running = false;
    private boolean stop = false;

    MainFrame(String title) {
        super(title);
        setLayout(new GridBagLayout());
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.fill = GridBagConstraints.NONE;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 1;
        gridBagConstraints.weighty = 1;
        add(countLabel, gridBagConstraints);
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.weightx = 1;
        gridBagConstraints.weighty = 1;
        add(statusLabel, gridBagConstraints);
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.weightx = 1;
        gridBagConstraints.weighty = 1;
        add(startButton, gridBagConstraints);
        startButton.addActionListener(actionEvent -> {
            if (!running) {
                startButton.setText("Stop");
                running = true;
                start();
            } else {
                stop = !stop;
                startButton.setText(stop ? "Start" : "Stop");
            }
        });
        setSize(200, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void start() {
        SwingWorker<Boolean, Integer> swingWorker = new SwingWorker<>() {
            @Override
            protected Boolean doInBackground() throws Exception {
                for (int i = 0; i < 100; i++) {
                    Thread.sleep(100);
                    while (stop) {
                        Thread.sleep(100);
                    }
                    publish(i);
                }
                return true;
            }

            @Override
            protected void process(List<Integer> chunks) {
                if (!chunks.isEmpty()) {
                    Integer chunk = chunks.get(chunks.size() - 1);
                    countLabel.setText(String.valueOf(chunk));
                }
            }

            @Override
            protected void done() {
                Boolean status = null;
                try {
                    status = get();
                } catch (InterruptedException | ExecutionException e) {
                    e.printStackTrace();
                } finally {
                    statusLabel.setText("Task completed, status: " + status);
                    countLabel.setText("-");
                    startButton.setText("Start");
                    running = false;
                    stop = false;
                }
            }
        };
        swingWorker.execute();
    }
}
