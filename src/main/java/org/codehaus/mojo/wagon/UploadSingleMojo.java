package org.codehaus.mojo.wagon;

/**
 * Licensed to the Apache Software Foundation (ASF) under one or more contributor license
 * agreements. See the NOTICE file distributed with this work for additional information regarding
 * copyright ownership. The ASF licenses this file to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance with the License. You may obtain a
 * copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */

import java.io.File;

import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.wagon.Wagon;
import org.apache.maven.wagon.WagonException;
import org.codehaus.plexus.util.StringUtils;

/**
 * Upload a single file with option to change name
 * 
 * @author Dan T. Tran
 * @goal upload-single
 * 
 * @requiresProject true
 */
public class UploadSingleMojo
    extends AbstractWagonMojo
{
    /**
     * Path to a local file to be uploaded
     * 
     * @parameter expression="${wagon.fromFile}" 
     * @required
     */
    private File fromFile;

    /**
     * Relative path to the URL of the remote directory
     * @parameter expression="${wagon.toFile}"
     * @required
     */
    private String toFile;

    protected void execute( Wagon wagon )
        throws MojoExecutionException, WagonException
    {
        if ( StringUtils.isBlank( toFile ) )
        {
            toFile = fromFile.getName();
        }
            
        this.getLog().info( "Uploading: " + fromFile + " " + wagon.getRepository().getUrl() + "/" + toFile );

        wagon.put( fromFile, toFile );

    }

}