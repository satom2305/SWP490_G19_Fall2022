import httpResource from "../common/http-resource"
import store from "../store/store"
import StorageService from "../common/storage.service"
import router from "../router/index"
import authHeader from "./auth-header"
import Configuration from "@/configuration";
import moment from "moment-timezone";
import JSONbig from 'json-bigint-native';
import $ from 'jquery'

/**
 *
 * @param file Blob
 * @param fileName string
 */
export function download(file, fileName) {
  const url = window.URL.createObjectURL(new Blob([file]));
  const link = document.createElement('a');
  link.href = url;
  link.setAttribute('download', fileName);
  document.body.appendChild(link);
  link.click();
}

export function getExtension(fileName = '') {
  const lastDotIndex = fileName.lastIndexOf('.');

  if (lastDotIndex < 0) {
    return '';
  }

  return fileName.substring(lastDotIndex + 1);
}

export function s2ab(s) {
  var buf = new ArrayBuffer(s.length);
  var view = new Uint8Array(buf);
  for (var i = 0; i != s.length; ++i) view[i] = s.charCodeAt(i) & 0xff;
  return buf;
}

export function formatDate(date) {
  if (!date) return null;
  return [date.getDate().padLeft(), (date.getMonth() + 1).padLeft(),
  date.getFullYear()].join('/');
}

export function formatDate2(value) {
  if (!value) return null;
  let date = new Date(value);
  return [date.getDate().padLeft(), (date.getMonth() + 1).padLeft(),
  date.getFullYear()].join('/');
}

export function formatDateYYYYMMDD(date) {
  if (!date) return null;
  return [date.getFullYear(), (date.getMonth() + 1).padLeft(), date.getDate().padLeft()].join('/');
}

export function formatDateTime(date) {
  if (!date) return '';
  return [date.getDate().padLeft(), (date.getMonth() + 1).padLeft(),
  date.getFullYear()].join('/') + ' ' +
    [date.getHours().padLeft(),
    date.getMinutes().padLeft(),
    date.getSeconds().padLeft()].join(':');
}

export function formatDateTimeCs(date) {
  if (!date) return '';
  return moment(date).format('HH:mm DD/MM/YYYY');
}

Number.prototype.padLeft = function (base, chr) {
  let len = (String(base || 10).length - String(this).length) + 1;
  return len > 0 ? new Array(len).join(chr || '0') + this : this;
}

export function parseDate(date) {
  if (!date) return null;
  const [day, month, year] = date.split("/");
  return `${year}-${month.padStart(2, "0")}-${day.padStart(2, "0")}`;
}

export function downloadExportExcel(result, fileName) {
  if (result != null) {
    const link = document.createElement('a')
    link.style.display = 'none'
    link.href = URL.createObjectURL(result)
    link.setAttribute('download', fileName)
    document.body.appendChild(link)
    link.click()
    document.body.removeChild(link)
  }
}

export function convertStringddMMyyyy2Date(str) {
  if (!str) return null;
  var date1 = str.split('/')
  var newDate = date1[1] + '/' + date1[0] + '/' + date1[2];
  var date = new Date(newDate);
  return date;
}

/*ham xu ly loi*/
export function parseApiError(error) {
  try {
    if (error && error.hasOwnProperty("data")) {
      const apiError = error.data;
      return {
        // status: apiError.status,
        // statusCode: error.status,
        // timestamp: apiError.time,
        // message: apiError.message
      }
    }
  } catch (parseError) {
    return {
      status: "INTERNAL_SERVER_ERROR",
      statusCode: 500,
      timestamp: new Date(),
      message: "Server is not responding ..."
    };
  }
}

// perform logout
export function performLogout() {
  const intervalName = store.getters.getIntervalName;
  if (intervalName) {
    clearInterval(intervalName);
  }
  StorageService.destroy("Token");
  StorageService.destroy("userInfo");
  router.push("/login").catch(() => {
  });
}

export async function logout() {
  let token = StorageService.get("Token");
  if (token) {
    // token = JSON.parse(token);
    StorageService.destroy("Token");
    StorageService.destroy("userInfo");
    router.push("/login").catch(() => {
    });
  }
}

export async function checkToken() {
  httpResource.defaults.baseURL = Configuration.value("baseURL");
  const response = await httpResource.get("/profile", { headers: authHeader() });
  return response.status;
}

export async function getAuthenticatedUser() {
  const intervalName = "Test";
  store.commit("setIntervalName", intervalName);
}


export const intervalMilliSeconds = 300000; // 5 minutes
/*export const intervalMilliSeconds = 30000;*/ // 1 minutes

let delayTimer;
export const doSearch = (callback) => {
  clearTimeout(delayTimer);
  delayTimer = setTimeout(function () {
    callback()
  }, 1000); // Will do the ajax stuff after 1000 ms, or 1 s
}

export const formatTime = (time, type) => {
  if (!time) return null
  return type === 'START'
    ? moment(time).startOf('day').format('YYYY-MM-DDTHH:mm:ss.SSSZ')
    : moment(time).startOf('day').add(1, 'day').format('YYYY-MM-DDTHH:mm:ss.SSSZ')
}

export const formatTimeV2 = (time, type, formatType) => {
  if (!time || !formatType) return null
  return type === 'START'
    ? moment(time).startOf('day').format(formatType)
    : moment(time).startOf('day').add(1, 'day').format(formatType)
}

export const formatTimeStamp = (time, type) => {
  if (!time) return null
  return type === 'START'
    ? new Date(moment(time).startOf('day')).getTime()
    : new Date(moment(time).startOf('day').add(1, 'day')).getTime()
}

export const convertSpaceToCommas = (value) => {
  if (!value) return '';
  return value.replaceAll(' ', ',')
}

export const parseDataFromStringV2 = (response) => {
  if (response.data instanceof Blob) {
    return response;
  }
  if (response && response.data) {
    response.data = JSONbig.parse(response.data)
  }
  return response
}

export const parseParam = (params, fieldConvert) => {
  if (params[fieldConvert]) params[fieldConvert] = JSONbig.stringify(params[fieldConvert])
  return params
}

export const formatCurrencyToString = (value) => {
  return value ? value.replace(/\D/g, "") : ''
}

export const strForSearch = (str) => {
  return str
    ? str
      .normalize("NFD")
      .toLowerCase()
      .replace(/[\u0300-\u036f]/g, "")
      .replace(/đ/g, "d")
    : str;
};
export const botChatAI = () => {
  !(function () {
    let e = document.createElement("script"),
      t = document.head || document.getElementsByTagName("head")[0];
    sessionStorage.clear();
    (e.src =
      "https://cdn.jsdelivr.net/npm/rasa-webchat@1.0.1/lib/index.js"),

      // Replace 1.x.x with the version that you want
      (e.async = !0),
      (e.onload = () => {
        window.WebChat.default(
          {
            selector: "#webchat",
            customData: { "language": "en" }, // arbitrary custom data. Stay minimal as this will be added to the socket
            socketUrl: "https://www.chatbottw.com/",
            socketPath: "/socket.io/",
            title: "Tree World",
            inputTextFieldHint: "Mời nhập câu hỏi",
            embedded: false,
            showFullScreenButton: true,
            showMessageDate: false,
            hideWhenNotConnected: false,
            displayUnreadCount: true,
            mainColor: "#7fad39",
            subtitle: 'Say hi and get started!',
            profileAvatar: "https://res.cloudinary.com/des083zke/image/upload/v1670951098/bot/pngtree-chatbot-color-icon-chat-bot-png-image_5274901_mpggry.png",
            openLauncherImage: "https://res.cloudinary.com/des083zke/image/upload/v1670951098/bot/pngtree-chatbot-color-icon-chat-bot-png-image_5274901_mpggry.png",
            showMessageDate: true,
            params: { "storage": "session" } // can be set to "local"  or "session". details in storage section.
          },
          null
        );
      }),
      t.insertBefore(e, t.firstChild);
  })();
}
export const handleJQuery = () => {
  const jQuery = document.createElement('script')
  jQuery.setAttribute(
    'src',
    'https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.1/jquery.min.js'
  )
  document.head.appendChild(jQuery)

  const jQuerySlick = document.createElement('script')
  jQuerySlick.setAttribute(
    'src',
    'https://cdnjs.cloudflare.com/ajax/libs/SlickNav/1.0.10/jquery.slicknav.min.js'
  )
  document.head.appendChild(jQuerySlick)

  console.log('loaded main.js')
  /*------------------
        Preloader
    --------------------*/
  $(window).on('load', function () {
    $('.loader').fadeOut()
    $('#preloder').delay(200).fadeOut('slow')

    /*------------------
          Gallery filter
      --------------------*/
    $('.featured__controls li').on('click', function () {
      $('.featured__controls li').removeClass('active')
      $(this).addClass('active')
    })
    if ($('.featured__filter').length > 0) {
      var containerEl = document.querySelector('.featured__filter')
      var mixer = mixitup(containerEl)
    }
  })

  /*------------------
        Background Set
    --------------------*/
  $('.set-bg').each(function () {
    var bg = $(this).data('setbg')
    $(this).css('background-image', 'url(' + bg + ')')
  })

  //Humberger Menu
  $('.humberger__open').on('click', function () {
    $('.humberger__menu__wrapper').addClass('show__humberger__menu__wrapper')
    $('.humberger__menu__overlay').addClass('active')
    $('body').addClass('over_hid')
  })

  $('.humberger__menu__overlay').on('click', function () {
    $('.humberger__menu__wrapper').removeClass(
      'show__humberger__menu__wrapper'
    )
    $('.humberger__menu__overlay').removeClass('active')
    $('body').removeClass('over_hid')
  })

  /*-----------------------
        Categories Slider
    ------------------------*/

  $('.hero__categories__all').on('click', function () {
    $('.hero__categories ul').slideToggle(400)
  })
  /*------------------
    Single Product
  --------------------*/
  $('.product__details__pic__slider img').on('click', function () {
    var imgurl = $(this).data('imgbigurl')
    var bigImg = $('.product__details__pic__item--large').attr('src')
    if (imgurl != bigImg) {
      $('.product__details__pic__item--large').attr({
        src: imgurl,
      })
    }
  })
}

export const verifyAccountRole = () => {
  let userInfo = localStorage.getItem('userInfo')
  if (userInfo && userInfo !== '') {
    let role = JSON.parse(userInfo).role
    if (role.includes('ADMIN') || role.includes('USER') || role.includes('STAFF')) {
      // request.headers["Authorization"] = 'Bearer ' + StorageService.get('Token')
      return true
    }
  }

  return false
};
