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

package com.github.ppadial.pihole.client;

import java.util.Arrays;
import java.util.Optional;

/**
 * HTTP TRStatus Codes values.
 *
 * @author Paulino Padial
 * @since 0.1.0
 */
public enum HttpStatusCode {

  //
  // ENUM VALUES
  // 1xx Info
  CONTINUE(100), SWITCHING_PROTOCOLS(101), PROCESSING(102),
  // 2xx Success
  OK(200), CREATED(201), ACCEPTED(202), NON_AUTHORITATIVE_INFORMATION(203), NO_CONTENT(
      204), RESET_CONTENT(205),
  PARTIAL_CONTENT(206), MULTI_STATUS(207), ALREADY_REPORTED(208), IM_USED(226),
  // 3xx Redirection
  MULTIPLE_CHOICES(300), MOVED_PERMANENTLY(301), MOVED_TEMPORARILY(302), SEE_OTHER(
      303), NOT_MODIFIED(304),
  USE_PROXY(305), TEMPORARY_REDIRECT(307),
  // 4xx Client Errors
  BAD_REQUEST(400), UNAUTHORIZED(401), PAYMENT_REQUIRED(402), FORBIDDEN(403), NOT_FOUND(
      404), METHOD_NOT_ALLOWED(405),
  NOT_ACCEPTABLE(406), PROXY_AUTHENTICATION_REQUIRED(407), REQUEST_TIMEOUT(408), CONFLICT(
      409), GONE(410),
  LENGTH_REQUIRED(411), PRECONDITION_FAILED(412), REQUEST_TOO_LONG(413), REQUEST_URI_TOO_LONG(414),
  UNSUPPORTED_MEDIA_TYPE(415), REQUESTED_RANGE_NOT_SATISFIABLE(416), EXPECTATION_FAILED(417),
  INSUFFICIENT_SPACE_ON_RESOURCE(419), METHOD_FAILURE(420), UNPROCESSABLE_ENTITY(422), LOCKED(423),
  FAILED_DEPENDENCY(424), TOO_MANY_REQUEST(429),
  // 5xx Server Errors
  INTERNAL_SERVER_ERROR(500), NOT_IMPLEMENTED(501), BAD_GATEWAY(502), SERVICE_UNAVAILABLE(
      503), GATEWAY_TIMEOUT(504),
  HTTP_VERSION_NOT_SUPPORTED(505), VARIANT_ALSO_NEGOTIATES(506), INSUFFICIENT_STORAGE(
      507), LOOP_DETECTED(508),
  NOT_EXTENDED(510), NETWORK_AUTHENTICATION_REQUIRED(511),

  // Unnofficial Codes (From Wikipedia: https://en.wikipedia.org/wiki/List_of_HTTP_status_codes)
  // General
  BLOCKED_BY_WINDOWS_PARENTAL_SERVICE(450), BANDWITH_LIMIT_EXCEEDED(
      509), NETWORK_READ_TIMEOUT_ERROR(598),
  // IIS Specific
  LOGIN_TIMEOUT(440), RETRY_WITH(449), REDIRECT(451),
  // Ngin Specific
  NO_RESPONSE(444), SSL_CERTIFICATE_ERROR(495), SSL_CERTIFICATE_REQUIRED(
      496), HTTP_RESQUEST_SENT_TO_HTTPS_PORT(497),
  CLIENT_CLOSED_REQUEST(499),
  // CloudFare Specific
  UNKOWN_ERROR(520), WEBSERVER_IS_DOWN(521), CONNECTION_TIMEOUT(522), ORIGIN_IS_UNREACHABLE(523),
  A_TIMEOUT_OCCURRED(524), SSL_HANDSHAKE_FAILED(525), INVALID_SSL_CERTIFICATE(526), RAILGUN_ERROR(
      527);
  // END ENUM VALUES
  //

  //
  // ENUM CODE
  private int value;

  HttpStatusCode(int value) {
    this.value = value;
  }

  public static HttpStatusCode fromCode(int httpCode) {
    Optional<HttpStatusCode> httpStatusCode = Arrays.stream(values())
        .filter(x -> x.value == httpCode).findFirst();
    if (httpStatusCode.isPresent()) {
      return httpStatusCode.get();
    } else {
      throw new IllegalArgumentException(
          "HttpCode " + httpCode + " not present into the enum values");
    }
  }

  public int value() {
    return this.value;
  }

  @Override
  public String toString() {
    return Integer.toString(this.value);
  }

  /**
   * Returns if the current value of the enum is an 1xx code.
   *
   * @return true if is an 1xx code
   */
  public boolean is1xxInformational() {
    return (this.value / 100) == 1;
  }

  /**
   * Returns if the current value of the enum is an 2xx code.
   *
   * @return true if is an 2xx code
   */
  public boolean is2xxSuccessful() {
    return (this.value / 100) == 2;
  }

  /**
   * Returns if the current value of the enum is an 3xx code.
   *
   * @return true if is an 3xx code
   */
  public boolean is3xxRedirection() {
    return (this.value / 100) == 3;
  }

  /**
   * Returns if the current value of the enum is an 4xx code.
   *
   * @return true if is an 4xx code
   */
  public boolean is4xxClientError() {
    return (this.value / 100) == 4;
  }

  /**
   * Returns if the current value of the enum is an 5xx code.
   *
   * @return true if is an 5xx code
   */
  public boolean is5xxServerError() {
    return (this.value / 100) == 5;
  }
  // END ENUM CODE
  //
}
