/*
 * Copyright (c) 2017. Paulino Padial
 *
 * Permission is hereby granted, free of charge, to any person obtaining
 * a copy of this software and associated documentation files (the
 * "Software"), to deal in the Software without restriction, including
 * without limitation the rights to use, copy, modify, merge, publish,
 * distribute, sublicense, and/or sell copies of the Software, and to
 * permit persons to whom the Software is furnished to do so, subject to
 * the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or
 * substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE
 * LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION
 * OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION
 * WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package com.github.ppadial.pihole.client.apiClient;

import com.github.ppadial.pihole.client.HttpStatusCode;
import java.io.ByteArrayOutputStream;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;

/**
 * ApiResponse mapper operations.
 *
 * @author Paulino Padial
 * @since 0.1.0
 */
public final class ApiResponseMapper {

  /**
   * Gets an ApiResponse from an HttpResponse.
   *
   * @param httpResponse the response object
   * @return the api response object
   * @throws MappingException an error during mapping operation
   * @since 0.1.0
   */
  public static ApiResponse from(HttpResponse httpResponse) throws MappingException {
    final ApiResponse apiResponse = new ApiResponse();

    try {
      // Sets the entiry underliying response object
      apiResponse.setUnderlyingHttpResponse(httpResponse);
      // Sets and convert the status code of the response
      apiResponse
          .setHttpStatusCode(HttpStatusCode.fromCode(httpResponse.getStatusLine().getStatusCode()));
      // Transform the response body and sets to the body property
      HttpEntity entity = httpResponse.getEntity();
      ByteArrayOutputStream os = new ByteArrayOutputStream();
      entity.writeTo(os);
      apiResponse.setBody(os.toString("UTF-8"));
    } catch (Exception exception) {
      throw new MappingException(exception);
    }

    return apiResponse;
  }

}
