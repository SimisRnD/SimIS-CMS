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

package com.simisinc.platform.application.datasets;

import com.simisinc.platform.application.DataException;
import com.simisinc.platform.domain.model.datasets.Dataset;
import com.simisinc.platform.domain.model.items.Collection;
import com.simisinc.platform.infrastructure.persistence.datasets.DatasetRepository;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.File;
import java.util.List;

/**
 * Converts a json file to item records
 *
 * @author matt rajkowski
 * @created 2/27/20 4:30 PM
 */
public class ConvertJsonFileCommand {

  private static Log LOG = LogFactory.getLog(ConvertJsonFileCommand.class);

  public static boolean convertFileToCollection(Dataset dataset, Collection collection) throws Exception {

    // Get a file handle
    File dataFile = DatasetFileCommand.getFile(dataset);
    if (dataFile == null) {
      throw new Exception("File was not found, dataset: " + dataset.getId());
    }

    // Load the records
    List<String[]> rows = LoadJsonCommand.loadRecords(dataset, Integer.MAX_VALUE, false);

    // Save the records
    int rowsProcessed = 0;
    for (String[] row : rows) {
      // Transform the row to item, then save
      boolean isSaved = SaveDatasetRowCommand.saveRecord(row, dataset, collection);
      if (!isSaved) {
        throw new DataException("Save error");
      }
      ++rowsProcessed;
      if (rowsProcessed % 100 == 0) {
        LOG.debug("..." + rowsProcessed);
        dataset.setRowsProcessed(rowsProcessed);
        DatasetRepository.updateRowsProcessed(dataset);
      }
    }
    dataset.setRowsProcessed(rowsProcessed);
    DatasetRepository.updateRowsProcessed(dataset);
    return true;
  }
}
