/*
 * Copyright 2022 SimIS Inc. (https://www.simiscms.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.simisinc.platform.application.cms;

import com.simisinc.platform.application.DataException;
import com.simisinc.platform.application.filesystem.FileSystemCommand;
import com.simisinc.platform.domain.model.cms.FileItem;
import com.simisinc.platform.domain.model.cms.FileVersion;
import com.simisinc.platform.infrastructure.persistence.cms.FileItemRepository;
import com.simisinc.platform.infrastructure.persistence.cms.FileVersionRepository;
import com.simisinc.platform.infrastructure.persistence.cms.FileVersionSpecification;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.File;
import java.util.List;

/**
 * Deletes files
 *
 * @author matt rajkowski
 * @created 12/12/18 4:45 PM
 */
public class DeleteFileCommand {

  private static Log LOG = LogFactory.getLog(DeleteFileCommand.class);

  public static boolean deleteFile(FileItem fileBean) throws DataException {

    // Verify the object
    if (fileBean == null || fileBean.getId() == -1) {
      throw new DataException("The file was not specified");
    }

    // Determine the files to delete
    FileVersionSpecification specification = new FileVersionSpecification();
    specification.setFileId(fileBean.getId());
    List<FileVersion> fileVersionList = FileVersionRepository.findAll(specification, null);

    LOG.debug("Version count: " + fileVersionList.size());

    // Remove the file
    if (FileItemRepository.remove(fileBean)) {
      // Delete all the files/versions
      String serverRootPath = FileSystemCommand.getFileServerRootPath();
      for (FileVersion fileVersion : fileVersionList) {
        String fileServerPath = fileVersion.getFileServerPath();
        if (StringUtils.isBlank(fileServerPath)) {
          continue;
        }
        File file = new File(serverRootPath + fileServerPath);
        if (file.exists() && file.isFile()) {
          file.delete();
        }
      }
      return true;
    }
    return false;
  }

}
