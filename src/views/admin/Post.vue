<template>
  <div>
    <page-title
      :heading="heading"
      :subheading="subheading"
      :icon="icon"
      :loading="loadingHeader"
    ></page-title>
    <b-card class="main-card">
      <template v-if="loadingHeader">
        <a-skeleton active :paragraph="{ rows: 5 }"></a-skeleton>
      </template>
      <template v-else>
        <div class="d-flex">
          <div style="width: 20rem" class="mr-2">
            <div class="label-form">Tên bài đăng</div>
            <b-input
              type="text"
              placeholder="Nhập tên bài đăng"
              v-model="dataFilter.productName"
              @input="fetchProductByName($event)"
            />
          </div>
          <b-button
            variant="primary"
            class="mr-2 mb-4 custom-btn-common"
            @click="handleSearch"
          >
            <font-awesome-icon :icon="['fas', 'search']" />
            Tìm kiếm
          </b-button>
          <b-button
            variant="light"
            @click="handleReset"
            class="mr-2 mb-4 custom-btn-common"
          >
            <font-awesome-icon :icon="['fas', 'eraser']" />
            Xóa lọc
          </b-button>
        </div>
        <div class="text-right">
          <b-button
            variant="info"
            class="mb-4"
            @click="navigateToCreatePost()"
            style="max-width: 200px"
          >
            <i class="fas fa-edit"></i> Thêm bài đăng
          </b-button>
        </div>
        <b-table
          :items="getPosts"
          :fields="visibleFields"
          :bordered="true"
          :hover="true"
          :fixed="true"
          :per-page="dataFilter.limit"
          :current-page="dataFilter.page"
          :foot-clone="false"
        >
          <template #cell(key)="row">
            {{ dataFilter.limit * (dataFilter.page - 1) + row.index + 1 }}
          </template>
          <template #cell(date)="row">
            {{ formatDateTime(row.item.date) }}
          </template>
          <template #cell(actions)="row">
            <div class="d-flex justify-content-center">
              <div class="px-3">
                <a
                  href="javascript:void(0)"
                  type="button"
                  v-b-tooltip.hover
                  title="Cập nhật"
                  @click.prevent="navigateToUpdatePost(row.item)"
                >
                  <i class="fas fa-edit" style="font-size: 1.1rem"></i>
                </a>
              </div>
              <div class="px-3">
                <a
                  href="javascript:void(0)"
                  type="button"
                  v-b-tooltip.hover
                  title="Xoá bài đăng"
                  @click.prevent="openModalDeletePost(row.item)"
                >
                  <i
                    class="fas fa-times"
                    style="font-size: 1.1rem; color: red"
                  ></i>
                </a>
              </div>
            </div>
          </template>
        </b-table>

        <b-row v-if="getPosts && getPosts.length > 0">
          <b-col class="pagination">
            <b-pagination
              v-model="dataFilter.page"
              :per-page="dataFilter.limit"
              :total-rows="getPosts.length"
              @change="changePage"
            ></b-pagination>
          </b-col>
          <b-col class="mt-1">
            <span class="text-muted">
              {{ fromPage }} đến {{ toPage }} trên {{ getPosts.length }} bản ghi
            </span>
          </b-col>
        </b-row>
        <b-row v-else class="justify-content-center">
          <span>Không tìm thấy bản ghi nào</span>
        </b-row>
      </template>
    </b-card>

    <b-modal
      hide-footer
      id="delete-post"
      :title="'Xác nhận xoá bài đăng'"
      :no-close-on-backdrop="true"
    >
      <div class="pb-3">
        Bạn có muốn xoá bài đăng
        <span class="font-weight-bold" v-if="this.currentPost">{{
          this.currentPost.title
        }}</span>
        không ?
      </div>
      <b-button class="mr-2 btn-light2 pull-right" @click="cancelDeletePost">
        Hủy
      </b-button>
      <b-button
        variant="primary pull-right"
        class="mr-2"
        type="submit"
        @click="handleDeletePost"
      >
        Đồng ý
      </b-button>
    </b-modal>
  </div>
</template>

<script>
import PageTitle from "../../Layout/Components/PageTitle";
import baseMixins from "../../components/mixins/base";
import { required } from "vuelidate/lib/validators";
import { mapGetters } from "vuex";
import router from "@/router";
import { formatDateTime } from "../../common/utils";
import { FETCH_POSTS, DELETE_POST } from "@/store/action.type";
const initDataFilter = {
  page: 1,
  limit: 10,
};
export default {
  name: "PostManagement",
  data() {
    return {
      subheading: "Tạo và quản lý các bài đăng",
      icon: "pe-7s-portfolio icon-gradient bg-happy-itmeo",
      heading: "Quản lý bài đăng",
      loadingHeader: true,
      dataFilter: Object.assign({}, { ...initDataFilter }),
      userInfo: localStorage.getItem("userInfo")
        ? JSON.parse(localStorage.getItem("userInfo"))
        : null,
      currentPost: null,
      fields: [
        {
          key: "key",
          label: "STT",
          tdClass: "align-middle",
          thClass: "align-middle",
          visible: true,
          thStyle: { width: "4%" },
        },
        {
          key: "title",
          label: "Tiêu đề bài đăng",
          visible: true,
          thStyle: { width: "8%" },
          thClass: "text-left align-middle",
        },
        {
          key: "date",
          label: "Ngày đăng",
          visible: true,
          thStyle: { width: "8%" },
          thClass: "text-left align-middle",
        },
        {
          key: "actions",
          label: "Chức năng",
          visible: true,
          thStyle: { width: "5%" },
          thClass: "text-center align-middle",
        },
      ],
    };
  },
  mixins: [baseMixins],
  components: {
    PageTitle,
  },
  mounted() {
    this.fetchPost();
  },
  computed: {
    ...mapGetters(["getPosts", "getCategory"]),
    visibleFields() {
      return this.fields.filter((field) => field.visible);
    },
    fromPage() {
      return (this.dataFilter.page - 1) * this.dataFilter.limit + 1;
    },
    toPage() {
      return this.getPosts.length
        ? this.dataFilter.page * this.dataFilter.limit >= this.getPosts.length
          ? this.getPosts.length
          : this.dataFilter.page * this.dataFilter.limit
        : 0;
    },
  },
  methods: {
    formatDateTime(date) {
      if (!date) return "";
      return formatDateTime(new Date(date));
    },
    changePage(e) {
      this.dataFilter.page = e;
    },
    async fetchPost() {
      let response = await this.$store.dispatch(FETCH_POSTS);
      if (response) {
        setTimeout(() => {
          if (this.loadingHeader) this.loadingHeader = !this.loadingHeader;
        }, 200);
      }
      if (response && response.data)
        this.$store.commit("setPosts", response.data.data);
    },
    navigateToUpdatePost(post) {
      if (!post.postId) return;
      this.$router.push({
        path: `/admin/post/update/${post.postId}`,
      });
    },
    navigateToCreatePost() {
      this.$router.push({
        path: `/admin/post/create`,
      });
    },
    openModalDeletePost(post) {
      this.$root.$emit("bv::show::modal", "delete-post");
      this.currentPost = { ...post };
    },
    cancelDeletePost() {
      this.currentPost = null;
      this.$root.$emit("bv::hide::modal", "delete-post");
    },
    async handleDeletePost() {
      if (!this.currentPost || !this.currentPost.postId) {
        this.$message.closeAll();
        this.$message({
          message: "Xoá bài đăng không thành công.",
          type: "error",
          showClose: true,
        });
        return;
      }
      let res = await this.$store.dispatch(
        DELETE_POST,
        this.currentPost.postId
      );
      if (res && res.status === 200) {
        this.$message.closeAll();
        this.$message({
          message: "Xoá bài đăng thành công.",
          type: "success",
          showClose: true,
        });
        this.fetchPost();
        this.cancelDeletePost();
      }
    },
  },
};
</script>

<style scoped></style>
