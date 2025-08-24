package Final;

private class BallListener implements ActionListener {
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == saveButton) {
            saveStudentData();
        }
    }
}