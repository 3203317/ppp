package cn.newcapec.framework.core.exception;

public class MaxUploadSizeExceededException extends BaseException
{
  private long maxUploadSize;
  private String message;
  private String code;

  public MaxUploadSizeExceededException()
  {
  }

  public MaxUploadSizeExceededException(long maxUploadSize, String message)
  {
    this.maxUploadSize = maxUploadSize;
    this.message = message;
  }
}