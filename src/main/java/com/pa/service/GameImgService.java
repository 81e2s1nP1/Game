package com.pa.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.pa.entity.GameImg;

public interface GameImgService {
	public List<GameImg> uploadImage(MultipartFile[] files) throws IOException;
}
