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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
    public ModelAndView listFiles (ModelAndView mv) {
        
        mv.addObject("blogTitle", "Freemarker Template Demo using Spring by Tacho");
        FileSystemView fsv = FileSystemView.getFileSystemView();
        File[] roots = fsv.getRoots();
        for (int i = 0; i < roots.length; i++)
        {
          System.out.println("Root: " + roots[i]);
        }
        
        System.out.println("Home directory: " + fsv.getHomeDirectory());

        System.out.println("File system roots returned by File.listRoots():");

        File[] f = File.listRoots();
        for (int i = 0; i < f.length; i++)
        {
          System.out.println("Drive: " + f[i]);
          System.out.println("Display name: " + fsv.getSystemDisplayName(f[i]));
          System.out.println("Is drive: " + fsv.isDrive(f[i]));
          System.out.println("Is floppy: " + fsv.isFloppyDrive(f[i]));
          System.out.println("Readable: " + f[i].canRead());
          System.out.println("Writable: " + f[i].canWrite());
        }
        String path = "/Users/antoniobarrachina/";
        mv.addObject("path", path);
        mv.setViewName("index");
        return mv;
    }
    
    
    @RequestMapping(value = "/directories")
    public @ResponseBody List<String> getDirectories (@RequestParam(name = "path", required = false) Optional<String> path, ModelAndView mv) {
        
        File file = new File("/Users/antoniobarrachina/"+path.orElse(""));
        
        
        String[] directories = file.list(new FilenameFilter() {
            
            @Override
            public boolean accept(File current, String name) {
              return new File(current, name).isDirectory();
            }
          });
        
        List <String> directoryList = new ArrayList<>(Arrays.asList(directories));
        return directoryList;
    }
    
    @RequestMapping(value = "/files")
    public @ResponseBody List<String> getFiles (@RequestParam(name = "dir", required = false) Optional<String> path, ModelAndView mv) {
        
        File file = new File(path.orElse(""));
        List <String> files = new ArrayList<>(Arrays.asList(file.list()));
        return files;
    }
    
    
    
}

