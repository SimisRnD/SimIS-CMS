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

package com.simisinc.platform.domain.model.cms;

import java.sql.Timestamp;

import com.simisinc.platform.application.cms.UrlCommand;
import com.simisinc.platform.domain.model.Entity;

/**
 * A specific version of a file
 *
 * @author matt rajkowski
 * @created 12/12/18 1:43 PM
 */
public class FileVersion extends Entity {

  private Long id = -1L;
  private long fileId = -1L;
  private long folderId = -1L;
  private long subFolderId = -1L;
  private long categoryId = -1L;
  private String filename = null;
  private String title = null;
  private String version = null;
  private String extension = null;
  private String fileServerPath = null;
  private long fileLength = -1;
  private String fileType = null;
  private String mimeType = null;
  private String fileHash = null;
  private String webPath = null;
  private int width = -1;
  private int height = -1;
  private String summary;
  private long createdBy = -1;
  private long modifiedBy = -1;
  private Timestamp created = null;
  private Timestamp modified = null;
  private long downloadCount = 0L;

  public FileVersion() {
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public long getFileId() {
    return fileId;
  }

  public void setFileId(long fileId) {
    this.fileId = fileId;
  }

  public long getFolderId() {
    return folderId;
  }

  public void setFolderId(long folderId) {
    this.folderId = folderId;
  }

  public long getSubFolderId() {
    return subFolderId;
  }

  public void setSubFolderId(long subFolderId) {
    this.subFolderId = subFolderId;
  }

  public long getCategoryId() {
    return categoryId;
  }

  public void setCategoryId(long categoryId) {
    this.categoryId = categoryId;
  }

  public String getFilename() {
    return filename;
  }

  public void setFilename(String filename) {
    this.filename = filename;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getVersion() {
    return version;
  }

  public void setVersion(String version) {
    this.version = version;
  }

  public String getExtension() {
    return extension;
  }

  public void setExtension(String extension) {
    this.extension = extension;
  }

  public String getFileServerPath() {
    return fileServerPath;
  }

  public void setFileServerPath(String fileServerPath) {
    this.fileServerPath = fileServerPath;
  }

  public long getFileLength() {
    return fileLength;
  }

  public void setFileLength(long fileLength) {
    this.fileLength = fileLength;
  }

  public String getFileType() {
    return fileType;
  }

  public void setFileType(String fileType) {
    this.fileType = fileType;
  }

  public String getMimeType() {
    return mimeType;
  }

  public void setMimeType(String mimeType) {
    this.mimeType = mimeType;
  }

  public String getFileHash() {
    return fileHash;
  }

  public void setFileHash(String fileHash) {
    this.fileHash = fileHash;
  }

  public int getWidth() {
    return width;
  }

  public void setWidth(int width) {
    this.width = width;
  }

  public int getHeight() {
    return height;
  }

  public void setHeight(int height) {
    this.height = height;
  }

  public String getSummary() {
    return summary;
  }

  public void setSummary(String summary) {
    this.summary = summary;
  }

  public long getCreatedBy() {
    return createdBy;
  }

  public void setCreatedBy(long createdBy) {
    this.createdBy = createdBy;
  }

  public long getModifiedBy() {
    return modifiedBy;
  }

  public void setModifiedBy(long modifiedBy) {
    this.modifiedBy = modifiedBy;
  }

  public Timestamp getCreated() {
    return created;
  }

  public void setCreated(Timestamp created) {
    this.created = created;
  }

  public Timestamp getModified() {
    return modified;
  }

  public void setModified(Timestamp modified) {
    this.modified = modified;
  }

  public long getDownloadCount() {
    return downloadCount;
  }

  public void setDownloadCount(long downloadCount) {
    this.downloadCount = downloadCount;
  }

  public String getWebPath() {
    return webPath;
  }

  public void setWebPath(String webPath) {
    this.webPath = webPath;
  }

  public String getUrl() {
    return webPath + "-" + id + "/" + UrlCommand.encodeUri(filename);
  }
}
