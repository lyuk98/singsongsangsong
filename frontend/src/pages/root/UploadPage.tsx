import React, { useRef, useState } from "react";
import styles from "./UploadPage.module.css";
import { MdLibraryMusic } from "react-icons/md";
import onDragImg from "./../../sources/imgs/inputimg.jpg";
// 참고해야할 사이트
// https://hojung-testbench.tistory.com/entry/React-%ED%8C%8C%EC%9D%BC-%EC%97%85%EB%A1%9C%EB%93%9C-%EA%B8%B0%EB%8A%A5-%EA%B5%AC%ED%98%84
// https://guiyomi.tistory.com/148

const UploadPage = () => {
  const [uploadFile, setUploadFile] = useState<undefined>(undefined);
  const [isActive, setIsActive] = useState<boolean>(false);
  const fileInputRef = useRef<HTMLInputElement>(null);

  const handleDragEnter = (event: React.DragEvent<HTMLLabelElement>) => {
    event.preventDefault();
    event.stopPropagation();
    console.log("handleDragEnter");
    setIsActive(true);
  };

  const handleDragOver = (event: React.DragEvent<HTMLLabelElement>) => {
    event.preventDefault();
    event.stopPropagation();
    console.log("handleDragOver");
  };

  const handleDragLeave = (event: React.DragEvent<HTMLLabelElement>) => {
    event.preventDefault();
    event.stopPropagation();
    setIsActive(false);
    console.log("handleDragLeave");
  };

  const handleDragDrop = (event: React.DragEvent<HTMLLabelElement>) => {
    event.preventDefault();
    event.stopPropagation();
    setIsActive(false);
    console.log("handleDragDrop");
  };

  return (
    <div className={`w-100 py-main ${styles.container}`}>
      <div className={`${styles.header}`}>
        <h1>곡 분석하기</h1>
        <p>곡을 업로드하고 자세한 정보를 얻어보세요</p>
      </div>
      <div className={`w-100 ${styles.uploadSection}`}>
        <h1>곡 업로드</h1>
        <label
          id="musicFileUploader"
          className={`bg-box flex-col-center ${styles.uploadBox} ${
            isActive ? styles.active : ""
          }`}
          onDragEnter={(event) => handleDragEnter(event)}
          onDragOver={(event) => handleDragOver(event)}
          onDragLeave={(event) => handleDragLeave(event)}
          onDrop={(event) => handleDragDrop(event)}
        >
          <input
            ref={fileInputRef}
            type="file"
            accept="audio.*"
            className={`${styles.file}`}
          />
          {/* {!isActive ? (
            <MdLibraryMusic
              size={96}
              className={`${isActive ? styles.iconActive : ""}`}
            />
          ) : (
            <div>
              <img src={onDragImg} alt="" />
            </div>
          )} */}

          {/* <h3>오디오 파일을 업로드 해주세요</h3> */}
        </label>
      </div>
    </div>
  );
};

export default UploadPage;
