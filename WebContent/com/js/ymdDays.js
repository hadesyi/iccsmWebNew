/**
 * 작성일 : 2004.03.27
 * 년도검사를 한다.
 * 1841~2043년 까지만 검사가 가능하다.
 * 년도가 검사 범위를 벗어나면 경고창 후 멈춘다.
 *
 * @param int
 * @return int
 */
function get_year(src) {
 if ((src < 1841) || (src > 2043 )) {
  alert('연도 범위는 1841 ~ 2043 까지입니다.');
  return;
 } else {
  return src;
 }
}

/**
 * 달이 12보다 크거나 1보다 작은지 검사한다.
 * 날짜가 잘못된 경우에는 경고창 후 멈춘다.
 *
 * @param int
 * @return int
 */
function get_month(src) {
 if ((src < 1) || (src > 12 )) {
  alert('월 범위는 1 ~ 12 까지입니다.');
  return;
 } else {
  return src;
 }
}

/**
 * 날짜가 1일보다 크고 src보다 작은 경우는 날짜를 반환한다.
 * 날짜가 잘못된 경우에는 경고창 후 멈춘다.
 *
 * @param int
 * @param int
 * @return int
 */
function get_day(src,day) {
 if ((src < 1) || (src > day )) {
  alert('일 범위가 틀립니다.');
  return;
 } else {
  return src;
 }
}

/**
 * 음력을 양력으로 바꾸어서 반환한다.
 *
 * @param string
 * return string
 */
function lunerCalenderToSolarCalenger ( input_day ) {
 var kk = [[1, 2, 4, 1, 1, 2, 1, 2, 1, 2, 2, 1],   /* 1841 */
     [2, 2, 1, 2, 1, 1, 2, 1, 2, 1, 2, 1],
     [2, 2, 2, 1, 2, 1, 4, 1, 2, 1, 2, 1],
     [2, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2],
     [1, 2, 1, 2, 2, 1, 2, 1, 2, 1, 2, 1],
     [2, 1, 2, 1, 5, 2, 1, 2, 2, 1, 2, 1],
     [2, 1, 1, 2, 1, 2, 1, 2, 2, 2, 1, 2],
     [1, 2, 1, 1, 2, 1, 2, 1, 2, 2, 2, 1],
     [2, 1, 2, 3, 2, 1, 2, 1, 2, 1, 2, 2],
     [2, 1, 2, 1, 1, 2, 1, 1, 2, 2, 1, 2],
     [2, 2, 1, 2, 1, 1, 2, 1, 2, 1, 5, 2],   /* 1851 */
     [2, 1, 2, 2, 1, 1, 2, 1, 2, 1, 1, 2],
     [2, 1, 2, 2, 1, 2, 1, 2, 1, 2, 1, 2],
     [1, 2, 1, 2, 1, 2, 5, 2, 1, 2, 1, 2],
     [1, 1, 2, 1, 2, 2, 1, 2, 2, 1, 2, 1],
     [2, 1, 1, 2, 1, 2, 1, 2, 2, 2, 1, 2],
     [1, 2, 1, 1, 5, 2, 1, 2, 1, 2, 2, 2],
     [1, 2, 1, 1, 2, 1, 1, 2, 2, 1, 2, 2],
     [2, 1, 2, 1, 1, 2, 1, 1, 2, 1, 2, 2],
     [2, 1, 6, 1, 1, 2, 1, 1, 2, 1, 2, 2],
     [1, 2, 2, 1, 2, 1, 2, 1, 2, 1, 1, 2],   /* 1861 */
     [2, 1, 2, 1, 2, 2, 1, 2, 2, 3, 1, 2],
     [1, 2, 2, 1, 2, 1, 2, 2, 1, 2, 1, 2],
     [1, 1, 2, 1, 2, 1, 2, 2, 1, 2, 2, 1],
     [2, 1, 1, 2, 4, 1, 2, 2, 1, 2, 2, 1],
     [2, 1, 1, 2, 1, 1, 2, 2, 1, 2, 2, 2],
     [1, 2, 1, 1, 2, 1, 1, 2, 1, 2, 2, 2],
     [1, 2, 2, 3, 2, 1, 1, 2, 1, 2, 2, 1],
     [2, 2, 2, 1, 1, 2, 1, 1, 2, 1, 2, 1],
     [2, 2, 2, 1, 2, 1, 2, 1, 1, 5, 2, 1],
     [2, 2, 1, 2, 2, 1, 2, 1, 2, 1, 1, 2],   /* 1871 */
     [1, 2, 1, 2, 2, 1, 2, 1, 2, 2, 1, 2],
     [1, 1, 2, 1, 2, 4, 2, 1, 2, 2, 1, 2],
     [1, 1, 2, 1, 2, 1, 2, 1, 2, 2, 2, 1],
     [2, 1, 1, 2, 1, 1, 2, 1, 2, 2, 2, 1],
     [2, 2, 1, 1, 5, 1, 2, 1, 2, 2, 1, 2],
     [2, 2, 1, 1, 2, 1, 1, 2, 1, 2, 1, 2],
     [2, 2, 1, 2, 1, 2, 1, 1, 2, 1, 2, 1],
     [2, 2, 4, 2, 1, 2, 1, 1, 2, 1, 2, 1],
     [2, 1, 2, 2, 1, 2, 2, 1, 2, 1, 1, 2],
     [1, 2, 1, 2, 1, 2, 5, 2, 2, 1, 2, 1],   /* 1881 */
     [1, 2, 1, 2, 1, 2, 1, 2, 2, 1, 2, 2],
     [1, 1, 2, 1, 1, 2, 1, 2, 2, 2, 1, 2],
     [2, 1, 1, 2, 3, 2, 1, 2, 2, 1, 2, 2],
     [2, 1, 1, 2, 1, 1, 2, 1, 2, 1, 2, 2],
     [2, 1, 2, 1, 2, 1, 1, 2, 1, 2, 1, 2],
     [2, 2, 1, 5, 2, 1, 1, 2, 1, 2, 1, 2],
     [2, 1, 2, 2, 1, 2, 1, 1, 2, 1, 2, 1],
     [2, 1, 2, 2, 1, 2, 1, 2, 1, 2, 1, 2],
     [1, 5, 2, 1, 2, 2, 1, 2, 1, 2, 1, 2],
     [1, 2, 1, 2, 1, 2, 1, 2, 2, 1, 2, 2],   /* 1891 */
     [1, 1, 2, 1, 1, 5, 2, 2, 1, 2, 2, 2],
     [1, 1, 2, 1, 1, 2, 1, 2, 1, 2, 2, 2],
     [1, 2, 1, 2, 1, 1, 2, 1, 2, 1, 2, 2],
     [2, 1, 2, 1, 5, 1, 2, 1, 2, 1, 2, 1],
     [2, 2, 2, 1, 2, 1, 1, 2, 1, 2, 1, 2],
     [1, 2, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1],
     [2, 1, 5, 2, 2, 1, 2, 1, 2, 1, 2, 1],
     [2, 1, 2, 1, 2, 1, 2, 2, 1, 2, 1, 2],
     [1, 2, 1, 1, 2, 1, 2, 5, 2, 2, 1, 2],
     [1, 2, 1, 1, 2, 1, 2, 1, 2, 2, 2, 1],   /* 1901 */
     [2, 1, 2, 1, 1, 2, 1, 2, 1, 2, 2, 2],
     [1, 2, 1, 2, 3, 2, 1, 1, 2, 2, 1, 2],
     [2, 2, 1, 2, 1, 1, 2, 1, 1, 2, 2, 1],
     [2, 2, 1, 2, 2, 1, 1, 2, 1, 2, 1, 2],
     [1, 2, 2, 4, 1, 2, 1, 2, 1, 2, 1, 2],
     [1, 2, 1, 2, 1, 2, 2, 1, 2, 1, 2, 1],
     [2, 1, 1, 2, 2, 1, 2, 1, 2, 2, 1, 2],
     [1, 5, 1, 2, 1, 2, 1, 2, 2, 2, 1, 2],
     [1, 2, 1, 1, 2, 1, 2, 1, 2, 2, 2, 1],
     [2, 1, 2, 1, 1, 5, 1, 2, 2, 1, 2, 2],   /* 1911 */
     [2, 1, 2, 1, 1, 2, 1, 1, 2, 2, 1, 2],
     [2, 2, 1, 2, 1, 1, 2, 1, 1, 2, 1, 2],
     [2, 2, 1, 2, 5, 1, 2, 1, 2, 1, 1, 2],
     [2, 1, 2, 2, 1, 2, 1, 2, 1, 2, 1, 2],
     [1, 2, 1, 2, 1, 2, 2, 1, 2, 1, 2, 1],
     [2, 3, 2, 1, 2, 2, 1, 2, 2, 1, 2, 1],
     [2, 1, 1, 2, 1, 2, 1, 2, 2, 2, 1, 2],
     [1, 2, 1, 1, 2, 1, 5, 2, 2, 1, 2, 2],
     [1, 2, 1, 1, 2, 1, 1, 2, 2, 1, 2, 2],
     [2, 1, 2, 1, 1, 2, 1, 1, 2, 1, 2, 2],   /* 1921 */
     [2, 1, 2, 2, 3, 2, 1, 1, 2, 1, 2, 2],
     [1, 2, 2, 1, 2, 1, 2, 1, 2, 1, 1, 2],
     [2, 1, 2, 1, 2, 2, 1, 2, 1, 2, 1, 1],
     [2, 1, 2, 5, 2, 1, 2, 2, 1, 2, 1, 2],
     [1, 1, 2, 1, 2, 1, 2, 2, 1, 2, 2, 1],
     [2, 1, 1, 2, 1, 2, 1, 2, 2, 1, 2, 2],
     [1, 5, 1, 2, 1, 1, 2, 2, 1, 2, 2, 2],
     [1, 2, 1, 1, 2, 1, 1, 2, 1, 2, 2, 2],
     [1, 2, 2, 1, 1, 5, 1, 2, 1, 2, 2, 1],
     [2, 2, 2, 1, 1, 2, 1, 1, 2, 1, 2, 1],   /* 1931 */
     [2, 2, 2, 1, 2, 1, 2, 1, 1, 2, 1, 2],
     [1, 2, 2, 1, 6, 1, 2, 1, 2, 1, 1, 2],
     [1, 2, 1, 2, 2, 1, 2, 2, 1, 2, 1, 2],
     [1, 1, 2, 1, 2, 1, 2, 2, 1, 2, 2, 1],
     [2, 1, 4, 1, 2, 1, 2, 1, 2, 2, 2, 1],
     [2, 1, 1, 2, 1, 1, 2, 1, 2, 2, 2, 1],
     [2, 2, 1, 1, 2, 1, 4, 1, 2, 2, 1, 2],
     [2, 2, 1, 1, 2, 1, 1, 2, 1, 2, 1, 2],
     [2, 2, 1, 2, 1, 2, 1, 1, 2, 1, 2, 1],
     [2, 2, 1, 2, 2, 4, 1, 1, 2, 1, 2, 1],   /* 1941 */
     [2, 1, 2, 2, 1, 2, 2, 1, 2, 1, 1, 2],
     [1, 2, 1, 2, 1, 2, 2, 1, 2, 2, 1, 2],
     [1, 1, 2, 4, 1, 2, 1, 2, 2, 1, 2, 2],
     [1, 1, 2, 1, 1, 2, 1, 2, 2, 2, 1, 2],
     [2, 1, 1, 2, 1, 1, 2, 1, 2, 2, 1, 2],
     [2, 5, 1, 2, 1, 1, 2, 1, 2, 1, 2, 2],
     [2, 1, 2, 1, 2, 1, 1, 2, 1, 2, 1, 2],
     [2, 2, 1, 2, 1, 2, 3, 2, 1, 2, 1, 2],
     [2, 1, 2, 2, 1, 2, 1, 1, 2, 1, 2, 1],
     [2, 1, 2, 2, 1, 2, 1, 2, 1, 2, 1, 2],   /* 1951 */
     [1, 2, 1, 2, 4, 2, 1, 2, 1, 2, 1, 2],
     [1, 2, 1, 1, 2, 2, 1, 2, 2, 1, 2, 2],
     [1, 1, 2, 1, 1, 2, 1, 2, 2, 1, 2, 2],
     [2, 1, 4, 1, 1, 2, 1, 2, 1, 2, 2, 2],
     [1, 2, 1, 2, 1, 1, 2, 1, 2, 1, 2, 2],
     [2, 1, 2, 1, 2, 1, 1, 5, 2, 1, 2, 2],
     [1, 2, 2, 1, 2, 1, 1, 2, 1, 2, 1, 2],
     [1, 2, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1],
     [2, 1, 2, 1, 2, 5, 2, 1, 2, 1, 2, 1],
     [2, 1, 2, 1, 2, 1, 2, 2, 1, 2, 1, 2],   /* 1961 */
     [1, 2, 1, 1, 2, 1, 2, 2, 1, 2, 2, 1],
     [2, 1, 2, 3, 2, 1, 2, 1, 2, 2, 2, 1],
     [2, 1, 2, 1, 1, 2, 1, 2, 1, 2, 2, 2],
     [1, 2, 1, 2, 1, 1, 2, 1, 1, 2, 2, 1],
     [2, 2, 5, 2, 1, 1, 2, 1, 1, 2, 2, 1],
     [2, 2, 1, 2, 2, 1, 1, 2, 1, 2, 1, 2],
     [1, 2, 2, 1, 2, 1, 5, 2, 1, 2, 1, 2],
     [1, 2, 1, 2, 1, 2, 2, 1, 2, 1, 2, 1],
     [2, 1, 1, 2, 2, 1, 2, 1, 2, 2, 1, 2],
     [1, 2, 1, 1, 5, 2, 1, 2, 2, 2, 1, 2],   /* 1971 */
     [1, 2, 1, 1, 2, 1, 2, 1, 2, 2, 2, 1],
     [2, 1, 2, 1, 1, 2, 1, 1, 2, 2, 2, 1],
     [2, 2, 1, 5, 1, 2, 1, 1, 2, 2, 1, 2],
     [2, 2, 1, 2, 1, 1, 2, 1, 1, 2, 1, 2],
     [2, 2, 1, 2, 1, 2, 1, 5, 2, 1, 1, 2],
     [2, 1, 2, 2, 1, 2, 1, 2, 1, 2, 1, 1],
     [2, 2, 1, 2, 1, 2, 2, 1, 2, 1, 2, 1],
     [2, 1, 1, 2, 1, 6, 1, 2, 2, 1, 2, 1],
     [2, 1, 1, 2, 1, 2, 1, 2, 2, 1, 2, 2],
     [1, 2, 1, 1, 2, 1, 1, 2, 2, 1, 2, 2],   /* 1981 */
     [2, 1, 2, 3, 2, 1, 1, 2, 2, 1, 2, 2],
     [2, 1, 2, 1, 1, 2, 1, 1, 2, 1, 2, 2],
     [2, 1, 2, 2, 1, 1, 2, 1, 1, 5, 2, 2],
     [1, 2, 2, 1, 2, 1, 2, 1, 1, 2, 1, 2],
     [1, 2, 2, 1, 2, 2, 1, 2, 1, 2, 1, 1],
     [2, 1, 2, 2, 1, 5, 2, 2, 1, 2, 1, 2],
     [1, 1, 2, 1, 2, 1, 2, 2, 1, 2, 2, 1],
     [2, 1, 1, 2, 1, 2, 1, 2, 2, 1, 2, 2],
     [1, 2, 1, 1, 5, 1, 2, 1, 2, 2, 2, 2],
     [1, 2, 1, 1, 2, 1, 1, 2, 1, 2, 2, 2],   /* 1991 */
     [1, 2, 2, 1, 1, 2, 1, 1, 2, 1, 2, 2],
     [1, 2, 5, 2, 1, 2, 1, 1, 2, 1, 2, 1],
     [2, 2, 2, 1, 2, 1, 2, 1, 1, 2, 1, 2],
     [1, 2, 2, 1, 2, 2, 1, 5, 2, 1, 1, 2],
     [1, 2, 1, 2, 2, 1, 2, 1, 2, 2, 1, 2],
     [1, 1, 2, 1, 2, 1, 2, 2, 1, 2, 2, 1],
     [2, 1, 1, 2, 3, 2, 2, 1, 2, 2, 2, 1],
     [2, 1, 1, 2, 1, 1, 2, 1, 2, 2, 2, 1],
     [2, 2, 1, 1, 2, 1, 1, 2, 1, 2, 2, 1],
     [2, 2, 2, 3, 2, 1, 1, 2, 1, 2, 1, 2],   /* 2001 */
     [2, 2, 1, 2, 1, 2, 1, 1, 2, 1, 2, 1],
     [2, 2, 1, 2, 2, 1, 2, 1, 1, 2, 1, 2],
     [1, 5, 2, 2, 1, 2, 1, 2, 2, 1, 1, 2],
     [1, 2, 1, 2, 1, 2, 2, 1, 2, 2, 1, 2],
     [1, 1, 2, 1, 2, 1, 5, 2, 2, 1, 2, 2],
     [1, 1, 2, 1, 1, 2, 1, 2, 2, 2, 1, 2],
     [2, 1, 1, 2, 1, 1, 2, 1, 2, 2, 1, 2],
     [2, 2, 1, 1, 5, 1, 2, 1, 2, 1, 2, 2],
     [2, 1, 2, 1, 2, 1, 1, 2, 1, 2, 1, 2],
     [2, 1, 2, 2, 1, 2, 1, 1, 2, 1, 2, 1],   /* 2011 */
     [2, 1, 6, 2, 1, 2, 1, 1, 2, 1, 2, 1],
     [2, 1, 2, 2, 1, 2, 1, 2, 1, 2, 1, 2],
     [1, 2, 1, 2, 1, 2, 1, 2, 5, 2, 1, 2],
     [1, 2, 1, 1, 2, 1, 2, 2, 2, 1, 2, 2],
     [1, 1, 2, 1, 1, 2, 1, 2, 2, 1, 2, 2],
     [2, 1, 1, 2, 3, 2, 1, 2, 1, 2, 2, 2],
     [1, 2, 1, 2, 1, 1, 2, 1, 2, 1, 2, 2],
     [2, 1, 2, 1, 2, 1, 1, 2, 1, 2, 1, 2],
     [2, 1, 2, 5, 2, 1, 1, 2, 1, 2, 1, 2],
     [1, 2, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1],   /* 2021 */
     [2, 1, 2, 1, 2, 2, 1, 2, 1, 2, 1, 2],
     [1, 5, 2, 1, 2, 1, 2, 2, 1, 2, 1, 2],
     [1, 2, 1, 1, 2, 1, 2, 2, 1, 2, 2, 1],
     [2, 1, 2, 1, 1, 5, 2, 1, 2, 2, 2, 1],
     [2, 1, 2, 1, 1, 2, 1, 2, 1, 2, 2, 2],
     [1, 2, 1, 2, 1, 1, 2, 1, 1, 2, 2, 2],
     [1, 2, 2, 1, 5, 1, 2, 1, 1, 2, 2, 1],
     [2, 2, 1, 2, 2, 1, 1, 2, 1, 1, 2, 2],
     [1, 2, 1, 2, 2, 1, 2, 1, 2, 1, 2, 1],
     [2, 1, 5, 2, 1, 2, 2, 1, 2, 1, 2, 1],   /* 2031 */
     [2, 1, 1, 2, 1, 2, 2, 1, 2, 2, 1, 2],
     [1, 2, 1, 1, 2, 1, 5, 2, 2, 2, 1, 2],
     [1, 2, 1, 1, 2, 1, 2, 1, 2, 2, 2, 1],
     [2, 1, 2, 1, 1, 2, 1, 1, 2, 2, 1, 2],
     [2, 2, 1, 2, 1, 4, 1, 1, 2, 1, 2, 2],
     [2, 2, 1, 2, 1, 1, 2, 1, 1, 2, 1, 2],
     [2, 2, 1, 2, 1, 2, 1, 2, 1, 1, 2, 1],
     [2, 2, 1, 2, 5, 2, 1, 2, 1, 2, 1, 1],
     [2, 1, 2, 2, 1, 2, 2, 1, 2, 1, 2, 1],
     [2, 1, 1, 2, 1, 2, 2, 1, 2, 2, 1, 2],   /* 2041 */
     [1, 5, 1, 2, 1, 2, 1, 2, 2, 2, 1, 2],
     [1, 2, 1, 1, 2, 1, 1, 2, 2, 1, 2, 2]];

// var gan = new Array("甲","乙","丙","丁","戊","己","庚","辛","壬","癸");
// var jee = new Array("子","丑","寅","卯","辰","巳","午","未","申","酉","戌","亥");
// var ddi = new Array("쥐","소","범","토끼","용","뱀","말","양","원숭이","닭","개","돼지");
 var week = new Array("일","월","화","수","목","금","토");

 var md = new Array(31,0,31,30,31,30,31,31,30,31,30,31);

 var year =input_day.substring(0,4);
 var month =input_day.substring(4,6);
 var day =input_day.substring(6,8);

 // 음력에서 양력으로 변환
 var lyear, lmonth, lday, leapyes;
 var syear, smonth, sday;
 var mm, y1, y2, m1;
 var i, j, k1, k2, leap, w;
 var td, y;
 lyear = get_year(year);        // 년도 check
 lmonth = get_month(month);     // 월 check

 y1 = lyear - 1841;
 m1 = lmonth - 1;
 leapyes = 0;
 if (kk[y1][m1] > 2)  {
     if (document.frmTest.yoon[0].checked) {
     leapyes = 1;
     switch (kk[y1][m1]) {
        case 3:
        case 5:
          mm = 29;
          break;
        case 4:
        case 6:
          mm = 30;
          break;
      }
     } else {
     switch (kk[y1][m1]) {
       case 1:
       case 3:
       case 4:
         mm = 29;
         break;
       case 2:
       case 5:
       case 6:
         mm = 30;
         break;
     } // end of switch
    } // end of if
 } // end of if

   lday = get_day(day, mm);

   td = 0;
   for (i=0; i<y1; i++) {
   for (j=0; j<12; j++) {
      switch (kk[i][j]) {
        case 1:
          td = td + 29;
          break;
        case 2:
          td = td + 30;
          break;
        case 3:
          td = td + 58;    // 29+29
          break;
        case 4:
          td = td + 59;    // 29+30
          break;
        case 5:
          td = td + 59;    // 30+29
          break;
        case 6:
          td = td + 60;    // 30+30
          break;
        } // end of switch
   } // end of for
 } // end of for

 for (j=0; j<m1; j++) {
  switch (kk[y1][j]) {
    case 1:
      td = td + 29;
      break;
    case 2:
      td = td + 30;
      break;
    case 3:
      td = td + 58;    // 29+29
      break;
    case 4:
      td = td + 59;    // 29+30
      break;
    case 5:
      td = td + 59;    // 30+29
      break;
    case 6:
      td = td + 60;    // 30+30
      break;
  } // end of switch
  } // end of for

  if (leapyes == 1) {
  switch(kk[y1][m1]) {
    case 3:
    case 4:
      td = td + 29;
      break;
    case 5:
    case 6:
      td = td + 30;
      break;
   } // end of switch
  } // end of switch

  td =  td + parseFloat(lday) + 22;
  // td : 1841 년 1 월 1 일 부터 원하는 날짜까지의 전체 날수의 합
  y1 = 1840;
  do {
  y1 = y1 +1;
  if  ((y1 % 400 == 0) || ((y1 % 100 != 0) && (y1 % 4 == 0))) {
    y2 = 366;
  }
  else {
    y2 = 365;
  }
  if (td <= y2) {
    break;
  }
  else {
    td = td- y2;
  }
  } while(1); // end do-While
 
 syear = y1;
 md[1] = parseInt(y2) -337;
 m1 = 0;
 do {
 m1= m1 + 1;
 if (td <= md[m1-1]) {
  break;
 }
 else {
  td = td - md[m1-1];
 }
 } while(1); // end of do-While

 smonth = parseInt(m1);
 sday = parseInt(td);

 // 월이 한자리인경우에는 앞에 0을 붙혀서 반환
 if ( smonth < 10 ) {
  smonth = "0" + smonth;
 }
 // 일이 한자리인경우에는 앞에 0을 붙혀서 반환
 if ( sday < 10 ) {
  sday = "0" + sday;
 }

 return new String( syear + smonth + sday );
}

/**
  * 주말인 검사한다.
  * 입력형식 : 2004년 3월 1일 --> 20040301
  * 주말이면 false반환
  * @param string
  * @return boolean
  */
 function isWeekend( yyyymmdd ) {
  var yyyy = parseInt( yyyymmdd.substring( 0, 4 ), 10 );
  var mm  = ( parseInt( yyyymmdd.substring( 4, 6 ), 10 ) - 1 );
  var dd  = parseInt( yyyymmdd.substring( 6, 8 ), 10 );
  var date = new Date( yyyy, mm, dd ); //Date 개체를 만듭니다.
 
  // 토요일, 일요일인 경우는 false반환
  if ( date.getDay() == 6 || date.getDay() == 0 ) {
   return true;
  } else {
   return false;
  }
 }
 
 /**
 * 입력한 날짜가 공유일인지 검사를 한다.
 * 공휴일인 경우에는 경고창 후 멈춘다.
 * 입력형식 : 2004년 3월 1일 --> 20040301
 *
 * @param string
 */
function isHoliday( yyyymmdd ) {
 // 검사년도
 var yyyy = yyyymmdd.substring( 0, 4 );
 var holidays = new Array();

 // 음력 공휴일을 양력으로 바꾸어서 입력
 var tmp01 = lunerCalenderToSolarCalenger( yyyy + "0101" );// 음력설날
 var tmp02 = lunerCalenderToSolarCalenger( yyyy + "0815" );// 음력추석
 holidays[0] = tmp01 - 1; // 음력설 첫째날
 holidays[1] = tmp01;   // 음력설 둘째날
 holidays[2] = tmp01 + 1; // 음력설 셋째날
 holidays[3] = tmp02 - 1; // 추석 첫째날
 holidays[4] = tmp02;   // 추석 둘째날
 holidays[5] = tmp02 + 1; // 추석 셋째날 
 holidays[6] = lunerCalenderToSolarCalenger( yyyy + "0408" ); // 석가탄신일

 // 양력 공휴일 입력
 holidays[7] = yyyy + "0101";  // 양력설날
 holidays[8] = yyyy + "0301";  // 삼일절
 holidays[9] = yyyy + "0405";  // 식목일
 holidays[10] = yyyy + "0505";  // 어린이날
 holidays[11] = yyyy + "0606";  // 현충일
 holidays[12] = yyyy + "0717";  // 제헌절
 holidays[13] = yyyy + "0815";  // 광복절
 holidays[14] = yyyy + "1003";  // 개천절
 holidays[15] = yyyy + "1225";  // 성탄절

 for ( var i=0; i<holidays.length ; i++ ) {
  if ( holidays[i] == yyyymmdd ) {
   return true ;
  }
 }
}

 /**
 * 입력한 날짜가 토요일, 일요일, 공휴일인지 검사를 한다.
 * 입력형식 : 2004년 3월 1일 --> 20040301
 *
 * @param string
 */

function isHolidayWeekendCheck( yyyymmdd ) {
 if ( !isHoliday( yyyymmdd ) || !isWeekend( yyyymmdd ) ) {
  alert( "토요일, 일요일, 공휴일에는 거래가 불가능합니다." );
  return true;
 } else {
  return false;
 }
} 

