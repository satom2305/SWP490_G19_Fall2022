<template>
    <div>
        <div class="humberger__menu__overlay"></div>
    <div class="humberger__menu__wrapper">
      <div class="humberger__menu__logo">
        <a href="/"><img src="img/logo.png" alt=""/></a>
      </div>
      <div class="humberger__menu__cart">
        <!-- <ul>
          <li>
            <a href="#">
              <font-awesome-icon icon="fa fa-heart" /> <span>1</span>
            </a>
          </li>
          <li>
            <a href="#">
              <font-awesome-icon icon="fa fa-shopping-bag" /> <span>3</span>
            </a>
          </li>
        </ul> -->
        <!-- <div class="header__cart__price">item: <span>$150.00</span></div> -->
      </div>
      <!--      <div class="humberger__menu__widget">-->
      <!--        <div class="header__top__right__language">-->
      <!--          <img src="img/language.png" alt="" />-->
      <!--          <div>English</div>-->
      <!--          <span class="arrow_carrot-down"></span>-->
      <!--          <ul>-->
      <!--            <li><a href="#">Spanis</a></li>-->
      <!--            <li><a href="#">English</a></li>-->
      <!--          </ul>-->
      <!--        </div>-->
      <!--        <div class="header__top__right__auth">-->
      <!--          <a href="#"><font-awesome-icon icon="fa fa-user" /> Login</a>-->
      <!--        </div>-->
      <!--      </div>-->
      <nav class="humberger__menu__nav mobile-menu">
        <ul>
              <li class="active">
                <a href="/">Trang chủ</a>
              </li>
              <li>
                <a href="/shop-product">Sản phẩm</a>
              </li>
              <li>
                <a href="#">Chức năng</a>
                <ul class="header__menu__dropdown">
                  <li>
                    <a href="/cart">Giỏ hàng</a>
                  </li>
                  <li>
                    <a href="/my-order">Đơn hàng của tôi</a>
                  </li>
                  <li>
                    <a href="/contact">Liên hệ </a>
                  </li>
                </ul>
              </li>
              <li>
                <a href="/blog">Tin tức</a>
              </li>
            </ul>
      </nav>
      <div id="mobile-menu-wrap"></div>
         <!-- <div class="header__top__right__social">-->
      <!--      <a href="#"><font-awesome-icon icon="fa fa-facebook"/></a>-->
      <!--      <a href="#"><font-awesome-icon icon="fa fa-twitter"/></a>-->
      <!--      <a href="#"><font-awesome-icon icon="fa fa-linkedin"/></a>-->
      <!--      <a href="#"><font-awesome-icon icon="fa fa-pinterest-p"/></a>-->
      <!--    </div> -->
      <div class="humberger__menu__contact">
        <ul>
          <li>
            <font-awesome-icon icon="fa fa-envelope" /> treeworld@gmail.com
          </li>
          <li>Giao hàng tận nơi</li>
        </ul>
      </div>
    </div>
</div>
</template>
<script>

export default {
  name: "Humberger",
  components: {},
};
</script>