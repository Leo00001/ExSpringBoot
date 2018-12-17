package com.baiyuas.boot.conditional;

public class LinuxListCmd implements ListCommand {

    @Override
    public void showListCmd() {
        System.out.println("Linux cmd ls -al");
    }
}
