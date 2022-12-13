<template>
    <div>
      <page-title :heading="heading" :subheading="subheading" :icon="icon" :loading="loadingHeader"></page-title>
      <b-card class="main-card">
        <template v-if="loadingHeader">
          <a-skeleton active :paragraph="{ rows: 5 }"></a-skeleton>
        </template>
        <template v-else>
          <div class="d-flex">
          <div style="width: 20rem" class="mr-2">
            <div class="label-form">Tên người dùng</div>
            <b-input
              type="text"
              placeholder="Nhập tên người dùng"
              v-model="dataFilter.usernameSearch"
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
          <!-- <div class="search-area mb-3">
            <b-row class="mb-3">
              <b-col md="2">
                <div class="label-form">Tên đăng nhập</div>
                <b-input
                  type="text"
                  placeholder="Nhập tên đăng nhập"
                  v-model.trim="dataFilter.username"
                />
              </b-col>
              <b-col md="2">
                <div class="label-form">Email</div>
                <b-input
                  type="text"
                  placeholder="Nhập email"
                  v-model.trim="dataFilter.email"
                />
              </b-col>
            </b-row>
            <b-row class="mb-3">
              <b-col md="6">
                <b-button variant="primary" class="mr-2" @click="handleSearch">
                  <font-awesome-icon :icon="['fas', 'search']" />
                  Tìm kiếm
                </b-button>
                <b-button variant="light" @click="handleResetFilter">
                  <font-awesome-icon :icon="['fas', 'eraser']" />
                  Xóa lọc
                </b-button>
              </b-col>
            </b-row>
          </div> -->
          <div class="text-right">
            <b-button variant="info" class="mb-4" @click="handleOpenModalUpdateUser(null, false)"
              style="max-width: 200px">
              <i class="fas fa-edit"></i> Thêm tài khoản
            </b-button>
          </div>
          <b-table
            :items="getUsers"
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
            <template #cell(status)="row" style="text-align: center">
              <b-badge class="badge-active" v-if="row.item.status">
                Hoạt động
              </b-badge>
              <b-badge class="badge-inactive" v-if="!row.item.status">
                Không hoạt động
              </b-badge>
            </template>
            <template #cell(role)="row" style="text-align: center">
              <div v-if="checkRole(row.item, 'ADMIN')">
                <div class="text-success mb-2"><i class="fas fa-user-shield"></i></div>
                <div>Admin</div>
              </div>
              <div v-if="checkRole(row.item, 'USER')">
                <div class="text-success mb-2"><i class="fas fa-user"></i></div>
                <div>Người dùng</div>
              </div>
              <div v-if="checkRole(row.item, 'STAFF')">
                <div class="text-success mb-2"><i class="fas fa-user-tie"></i></div>
                <div>Nhân viên</div>
              </div>
            </template>
            <template #cell(actions)="row">
              <div class="d-flex justify-content-center">
                <!-- <div class="px-3">
                  <a href="javascript:void(0)" type="button" v-b-tooltip.hover title="Cập nhật"
                    @click.prevent="handleOpenModalUpdateUser(row.item, true)">
                    <i class="fas fa-user-edit" style="font-size: 1.1rem"></i>
                  </a>
                </div> -->
                <div class="px-3" v-if="row.item.status">
                  <a href="javascript:void(0)" type="button" v-b-tooltip.hover title="Vô hiệu tài khoản"
                    @click.prevent="handleOpenModalDeleteUser(row.item)">
                    <i class="fas fa-user-times" style="font-size: 1.1rem; color: red"></i>
                  </a>
                </div>
              </div>
            </template>
          </b-table>

          <b-row v-if="getUsers && getUsers.length > 0">
            <b-col class="pagination">
              <b-pagination
                v-model="dataFilter.page"
                :per-page="dataFilter.limit"
                :total-rows="getUsers.length"
                @change="changePage"
              ></b-pagination>
            </b-col>
            <b-col class="mt-1">
              <span class="text-muted">
                {{ fromPage }} đến {{ toPage }} trên {{ getUsers.length }} bản ghi
              </span>
            </b-col>
          </b-row>
        <b-row v-else class="justify-content-center">
          <span>Không tìm thấy bản ghi nào</span>
        </b-row>
        </template>
      </b-card>
  
      <b-modal id="update-user" :title="isUpdateUser ? 'Cập nhật tài khoản' : 'Thêm tài khoản'"
        :no-close-on-backdrop="true" size="lg" @hidden="handleCancelUpdateUser">
        <b-form-group>
          <label>Tên đăng nhập<span class="text-danger">*</span>:</label>
          <b-form-input
            id="input-username"
            v-model.trim="$v.currentData.username.$model"
            maxlength="1000"
            placeholder="Nhập tên đăng nhập"
            :class="{ 'is-invalid': validationStatus($v.currentData.username) }"
          ></b-form-input>
          <div
            v-if="!$v.currentData.username.required"
            class="invalid-feedback"
          >
            Tên đăng nhập không được để trống.
          </div>
        </b-form-group>
        <b-form-group>
          <label>Mật khẩu<span class="text-danger">*</span>:</label>
          <b-form-input
            id="input-password"
            v-model.trim="$v.currentData.password.$model"
            :maxlength="maxLengthPassword"
            placeholder="Nhập mật khẩu"
            :type="inputTypePassword"
            :class="{ 'is-invalid': validationStatus($v.currentData.password) }"
          ></b-form-input>
          <div
            v-if="!$v.currentData.password.required"
            class="invalid-feedback"
          >
            Mật khẩu không được để trống.
          </div>
        </b-form-group>
        <b-form-group>
          <label>Email:<span class="text-danger">*</span>:</label>
          <b-form-input
            id="input-email"
            v-model.trim="$v.currentData.email.$model"
            placeholder="Nhập mật khẩu"
            type="text"
            :class="{ 'is-invalid': validationStatus($v.currentData.email) }"
          ></b-form-input>
          <div
            v-if="!$v.currentData.email.required"
            class="invalid-feedback"
          >
            Địa chỉ email không được để trống.
          </div>
          <div
            v-if="!$v.currentData.email.validEmail"
            class="invalid-feedback"
          >
            Địa chỉ email không hợp lệ.
          </div>
        </b-form-group>
        <b-form-group v-if="!isUpdateUser">
          <label>Vai trò <span class="text-danger">*</span>:</label>
          <multiselect
            v-model="currentSelectRole"
            track-by="text"
            label="text"
            :show-labels="false"
            placeholder="Chọn"
            :options="roleOption"
            :searchable="true"
            :allowEmpty="false"
          >
            <template slot="singleLabel" slot-scope="{ option }">
              {{ option.text }}
            </template>
          </multiselect>
        </b-form-group>
        <template #modal-footer>
          <b-button class="mr-2 btn-light2 pull-right" @click="handleCancelUpdateUser">
            Hủy
          </b-button>
          <b-button variant="primary pull-right" @click.prevent="handleSubmit">
            Đồng ý
          </b-button>
        </template>
      </b-modal>
  
      <b-modal hide-footer id="disable-user" :title="'Xác nhận vô hiệu tài khoản'" :no-close-on-backdrop="true">
        <div class="pb-3">
          Bạn có muốn vô hiệu tài khoản<span class="font-weight-bold" v-if="this.currentUser">{{ this.currentUser.username }}</span> không ?
        </div>
        <b-button class="mr-2 btn-light2 pull-right" @click="handleCancelDisableUser">
          Hủy
        </b-button>
        <b-button variant="primary pull-right" class="mr-2" type="submit" @click="handleDisableUser">
          Đồng ý
        </b-button>
      </b-modal>
    </div>
  </template>
    
  <script>
  import PageTitle from "../../Layout/Components/PageTitle";
  import baseMixins from "../../components/mixins/base";
  import { required, helpers } from "vuelidate/lib/validators";
  import { mapGetters } from "vuex";
  import router from '@/router';
  import { FETCH_USERS, FETCH_USER_BY_USERNAME, FETCH_USER_BY_ID, UPDATE_USER, CREATE_USER, DISABLE_USER, CHANGE_PASSWORD, CREATE_STAFF,SEARCH_USERS_BY_USERNAME } from "@/store/action.type";
  import debounce from "lodash/debounce";
  const initUser = {
    userId: null,
    username: null,
    password: null,
    email: null,
    statusId: null,
  };
  
  const initDataFilter = {
    usernameSearch:null,
    email: null,
    username: null,
    page: 1,
    limit: 10
  };

  const PASSWORD_LENGTH = 8

  const validEmail = helpers.regex('validEmail', /^\S+@\S+\.\S+$/)
  
  export default {
    name: "UserManagement",
    data() {
      this.searchUsersByUsername = debounce(this.searchUsersByUsername, 500);
      return {
        subheading: "Tạo và quản lý các tài khoản của người dùng",
        icon: "pe-7s-portfolio icon-gradient bg-happy-itmeo",
        heading: "Quản lý người dùng",
        dataUpdate: {},
        loadingHeader: true,
        userInfo: localStorage.getItem('userInfo') ? JSON.parse(localStorage.getItem('userInfo')) : null,
        isUpdateUser: false,
        currentData: Object.assign({}, { ...initUser }),
        dataFilter: Object.assign({}, initDataFilter),
        currentUser: null,
        currentSelectRole: {text: 'Người dùng', value: 'user'},
        maxLengthPassword: PASSWORD_LENGTH,
        inputTypePassword: 'password',
        fields: [
          { key: "key", label: "STT", tdClass: 'align-middle', thClass: 'align-middle', visible: true, thStyle: { width: '4%' } },
          { key: "username", label: "Tên tài khoản", visible: true, thStyle: { width: '8%' }, thClass: 'text-left align-middle' },
          { key: "email", label: "Email", visible: true, thStyle: { width: '8%' }, thClass: 'text-left align-middle' },
          { key: "role", label: "Vai trò", visible: true, thStyle: { width: '8%' }, thClass: 'text-center align-middle', tdClass: 'text-center align-middle' },
          { key: "status", label: "Trạng thái tài khoản", visible: true, thStyle: { width: '8%' }, thClass: 'text-center align-middle', tdClass: 'text-center align-middle' },
          {
            key: "actions",
            label: "Chức năng",
            visible: true,
            thStyle: { width: '5%' },
            thClass: 'text-center align-middle'
          }
        ],
        roleOption: [
          { text: 'Nhân viên', value: 'staff' },
          { text: 'Người dùng', value: 'user' }
        ]
      };
    },
    mixins: [baseMixins],
    validations: {
      currentData: {
        username: {
          required,
        },
        password: {
          required,
        },
        email: {
          required,
          validEmail,
        },
      },
    },
    components: {
      PageTitle,
    },
    mounted() {
      const dataSearch = this.$route.query.dataSearch;
  
      if (dataSearch) this.dataFilter = JSON.parse(String(dataSearch));
      this.fetchUsers();
    },
    computed: {
      ...mapGetters(["getUsers"]),
      visibleFields() {
        return this.fields.filter(field => field.visible)
      },
      fromPage() {
        return (this.dataFilter.page - 1) * this.dataFilter.limit + 1;
      },
      toPage() {
        return this.getUsers.length
          ? this.dataFilter.page * this.dataFilter.limit >= this.getUsers.length
          ? this.getUsers.length
          : this.dataFilter.page * this.dataFilter.limit
          : 0;
      },
      getUsersWithoutAdmin() {
        return this.getUsers.filter(user => !this.checkRole(user, 'ADMIN'))
      }
    },
    methods: {
    handleSearch() {
      if (
        !this.dataFilter.usernameSearch ||
        this.dataFilter.usernameSearch.trim() === ""
      ) {
        this.fetchUsers();
      } else {
        this.searchUsersByUsername(this.dataFilter.usernameSearch);
      }
    },
    handleReset() {
      this.dataFilter.usernameSearch = null;
      this.fetchUsers();
    },
    async searchUsersByUsername(value) {
      if (!value || value.trim() === "") return;
      let response = await this.$store.dispatch(
        SEARCH_USERS_BY_USERNAME,
        value.trim()
      );
      if (response && response.data)
        this.$store.commit("setUsers", response.data.data);
    },
      checkRole(user, roleName) {
        return user.role && user.role.filter(item => item.roleName === roleName).length > 0
      },
      validationStatus: function (validation) {
        return typeof validation != "undefined" ? validation.$error : false;
      },
      changePage(e) {
        this.dataFilter.page = e
        const dataSearch = this.$route.query.dataSearch;

        if ((dataSearch && dataSearch !== JSON.stringify(this.dataFilter)) || !dataSearch) {
          router.push({ path: '/admin/user', query: { dataSearch: JSON.stringify(this.dataFilter) }})
        }
        // this.fetchUsers();
      },
      // handleSearch() {
      //   this.dataFilter.page = 1
      //   router.push({ path: '/admin/user', query: { dataSearch: JSON.stringify(this.dataFilter) }})
      //   this.fetchUsers();
      // },
      // handleResetFilter() {
      //   this.$router.replace('/admin/user');
      //   this.dataFilter = Object.assign({}, initDataFilter);
      //   this.handleSearch();
      // },
      async fetchUsers() {
        let payload = {...this.dataFilter}
        let response = await this.$store.dispatch(FETCH_USERS, payload);
        if (response) {
          setTimeout(() => {
            if (this.loadingHeader) this.loadingHeader = !this.loadingHeader
          }, 200)
        }
  
        if (response && response.data) this.$store.commit("setUsers", response.data.data);
      },
      handleOpenModalUpdateUser(user, isUpdateUser) {
        this.isUpdateUser = isUpdateUser
  
        if (isUpdateUser) {
          this.currentData = Object.assign({}, {
            userId: user.userId,
            username: user.username,
            password: user.password,
            email: user.email,
            statusId: user.statusId,
          });
        }
        this.$root.$emit("bv::show::modal", "update-user");
      },
      handleCancelUpdateUser() {
        this.isUpdateUser = null
        this.currentUser = null
        this.currentData = Object.assign({}, { ...initUser })
        this.$nextTick(() => {
          this.$v.currentData.$reset();
        });
        this.$root.$emit("bv::hide::modal", "update-user");
      },
      async handleSubmit() {
        this.$v.$reset();
        this.$v.$touch();
        if (this.$v.currentData.$invalid) {
          return;
        }
        let payload = {
          username: this.currentData.username,
          password: this.currentData.password,
          email: this.currentData.email,
          statusId: this.currentData.statusId,
        }
        this.handleUpdateUser(payload)
      },
      async handleUpdateUser(payload) {
        let errorMsg = this.isUpdateUser ? "Cập nhật tài khoản không thành công." : "Thêm tài khoản không thành công."
        let successMsg = this.isUpdateUser ? "Cập nhật tài khoản thành công." : "Thêm tài khoản thành công."

        let payloadForUpdate = {
          userId: this.currentData.userId,
          userData: payload,
        }
        let res = await (this.isUpdateUser
          ? this.$store.dispatch(UPDATE_USER, payloadForUpdate)
          : (this.currentSelectRole && this.currentSelectRole.value === 'staff')
          ? this.$store.dispatch(CREATE_STAFF, payload)
          : this.$store.dispatch(CREATE_USER, payload))
        if (res) this.$message.closeAll()
        if (res.status === 200) {
          this.$message({
            message: successMsg,
            type: "success",
            showClose: true,
          });
          this.handleCancelUpdateUser()
          this.fetchUsers()
        } else {
          this.$message({
            message: errorMsg,
            type: "error",
            showClose: true,
          });
        }
      },
      handleOpenModalDeleteUser(user) {
        this.$root.$emit("bv::show::modal", "disable-user");
        this.currentUser = { ...user }
      },
      handleCancelDisableUser() {
        this.currentUser = null
        this.$root.$emit("bv::hide::modal", "disable-user");
      },
      async handleDisableUser() {
        if (!this.currentUser || !this.currentUser.userId) {
          this.$message.closeAll()
          this.$message({
            message: "Vô hiệu tài khoản không thành công.",
            type: "error",
            showClose: true,
          });
          return
        }
        let res = await this.$store.dispatch(DISABLE_USER, this.currentUser.userId)
        if (res && res.status === 200) {
          this.$message.closeAll()
          this.$message({
            message: "Vô hiệu tài khoản thành công.",
            type: "success",
            showClose: true,
          });
          this.fetchUsers();
          this.handleCancelDisableUser()
        }
      },
    },
  };
  </script>
    
  <style scoped>
  
  </style>
    