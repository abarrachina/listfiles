/**
 * All rights reserved.
 * Copyright (c) Ixxus Ltd 2015
 */
package com.ixxus.preflight.controllers;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.swing.filechooser.FileSystemView;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author antoniobarrachina
 *
 */
@Controller
public class ListFilesController {

    @RequestMapping("/")
    public ModelAndView listFiles (final ModelAndView mv) {

        mv.addObject("blogTitle", "Freemarker Template Demo using Spring by Tacho");
        final FileSystemView fsv = FileSystemView.getFileSystemView();
        final File[] roots = fsv.getRoots();
        for (final File root : roots) {
            System.out.println("Root: " + root);
        }

        System.out.println("Home directory: " + fsv.getHomeDirectory());

        System.out.println("File system roots returned by File.listRoots():");

        final File[] f = File.listRoots();
        for (final File element : f) {
            System.out.println("Drive: " + element);
            System.out.println("Display name: " + fsv.getSystemDisplayName(element));
            System.out.println("Is drive: " + fsv.isDrive(element));
            System.out.println("Is floppy: " + fsv.isFloppyDrive(element));
            System.out.println("Readable: " + element.canRead());
            System.out.println("Writable: " + element.canWrite());
        }
        final String path = "/Users/nazareth/";
        mv.addObject("path", path);
        mv.setViewName("index");
        return mv;
    }


    @RequestMapping(value = "/directories")
    public @ResponseBody List<String> getDirectories (@RequestParam(name = "path", required = false) final Optional<String> path, final ModelAndView mv) {

        final File file = new File(path.orElse(""));


        final String[] directories = file.list(new FilenameFilter() {

            @Override
            public boolean accept(final File current, final String name) {
                final File f = new File(current, name);
                final Boolean isDirectory = f.isDirectory();
                final Boolean isHidden = f.isHidden();
                return (isDirectory && !isHidden);
            }
        });

        final List <String> directoryList = new ArrayList<>(Arrays.asList(directories));
        return directoryList;
    }

    @RequestMapping(value = "/files")
    public @ResponseBody List<String> getFiles (@RequestParam(name = "dir", required = false) final Optional<String> path, final ModelAndView mv) {

        final File file = new File(path.orElse(""));

        final String[] files = file.list(new FilenameFilter() {

            @Override
            public boolean accept(final File current, final String name) {
                final File f = new File(current, name);
                final Boolean isDirectory = f.isDirectory();
                final Boolean isHidden = f.isHidden();
                return (!isDirectory && !isHidden);
            }
        });

        final List <String> filesList = new ArrayList<>(Arrays.asList(files));
        return filesList;
    }



}

