<template>
  <div>
    <!-- Page Preloder -->
    <!--  <div id="preloder">-->
    <!--    <div class="loader"></div>-->
    <!--  </div>-->

    <!-- Humberger Begin -->
    <Humberger />
    <!-- Humberger End -->

    <!-- Header Section Begin -->
    <UserHeader />
    <!-- Header Section End -->

    <!-- Hero Section Begin -->
    <SectionBegin />
    <!-- Hero Section End -->

    <!-- Breadcrumb Section Begin -->
    <!-- Breadcrumb Section End -->

    <!-- Contact Section Begin -->
    <section class="contact spad">
      <div class="container">
        <div class="row">
          <div class="col-lg-3 col-md-3 col-sm-6 text-center">
            <div class="contact__widget">
              <span class="icon_phone"></span>
              <h4>Số điện thoại</h4>
              <p>+84384137197</p>
            </div>
          </div>
          <div class="col-lg-3 col-md-3 col-sm-6 text-center">
            <div class="contact__widget">
              <span class="icon_pin_alt"></span>
              <h4>Địa chỉ</h4>
              <p>Thôn Bến, xã Phụng Công, huyện Văn Giang, tỉnh Hưng Yên</p>
            </div>
          </div>
          <div class="col-lg-3 col-md-3 col-sm-6 text-center">
            <div class="contact__widget">
              <span class="icon_clock_alt"></span>
              <h4>Open time</h4>
              <p>10:00 am to 23:00 pm</p>
            </div>
          </div>
          <div class="col-lg-3 col-md-3 col-sm-6 text-center">
            <div class="contact__widget">
              <span class="icon_mail_alt"></span>
              <h4>Email</h4>
              <p>hello@colorlib.com</p>
            </div>
          </div>
        </div>
      </div>
    </section>
    <!-- Contact Section End -->

    <!-- Map Begin -->
    <ModalMap />
    <!-- Map End -->

    <!-- Contact Form Begin -->
    <div class="contact-form spad">
      <div class="container">
        <div class="row">
          <div class="col-lg-12">
            <div class="contact__form__title">
              <h2>Leave Message</h2>
            </div>
          </div>
        </div>
        <form action="#">
          <div class="row">
            <div class="col-lg-6 col-md-6">
              <input type="text" placeholder="Your name" />
            </div>
            <div class="col-lg-6 col-md-6">
              <input type="text" placeholder="Your Email" />
            </div>
            <div class="col-lg-12 text-center">
              <textarea placeholder="Your message"></textarea>
              <button type="submit" class="site-btn">SEND MESSAGE</button>
            </div>
          </div>
        </form>
      </div>
    </div>
    <!-- Contact Form End -->

    <!-- Footer Section Begin -->
    <UserFooter />
    <!-- Footer Section End -->
  </div>
</template>

<script>
import { handleJQuery } from "../common/utils";
import UserHeader from "../Layout/Components/UserHeader";
import UserFooter from "../Layout/Components/UserFooter";
import Humberger from "../Layout/Components/Humberger";
import SectionBegin from "../Layout/Components/SectionBegin";
import ModalMap from "../Layout/Components/ModalMap";
export default {
  name: "contact",
  components: { UserHeader, SectionBegin, UserFooter, Humberger, ModalMap },
  created() {
    handleJQuery();
  },
};
</script>

<style scoped></style>
