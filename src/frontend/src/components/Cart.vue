<template>
  <v-container>
    <v-list two-line>
      <v-list-item
        v-for="cart in carts"
        :key="cart.id"
      >
        <v-list-item-action>
          <v-btn icon @click="getCartProducts(cart.id)">
            <v-icon>mdi-cart</v-icon>
          </v-btn>
        </v-list-item-action>

        <v-list-item-content>
          <v-list-item-title v-text="cart.createdAt.substring(0, 16)"></v-list-item-title>
          <v-list-item-subtitle v-text="cart.price"></v-list-item-subtitle>
        </v-list-item-content>
        <v-list-item-action>
          <v-btn icon @click="deleteCart(cart.id)">
            <v-icon color="red">mdi-trash-can-outline</v-icon>
          </v-btn>
        </v-list-item-action>
      </v-list-item>
      <div class="text-center">
        <v-btn fab color="success" dark @click="addCart()">
          <v-icon>mdi-plus</v-icon>
        </v-btn>
      </div>
    </v-list>
    <v-snackbar
      v-model="snackbar"
    >
      {{ message }}
      <v-btn
        color="success"
        text
        @click="snackbar = false"
      >
        Close
      </v-btn>
    </v-snackbar>
  </v-container>
</template>

<script>
export default {
    data: () => ({
        carts: [],
        message: '',
        snackbar: false
    }),
    methods: {
        getCarts() {
            this.$http.get("/api/carts").then(response => {
                this.carts = response.data;
            })
        },
        getCartProducts(cartId) {
            this.$router.push(`/carts/${cartId}/products`)
        },
        deleteCart(cartId) {
          this.$http.delete(`/api/carts/${cartId}`).then(response => {
            this.message=response.data.message
          }).then(() => {
            this.getCarts();
            this.snackbar=true;
          })
        },
        addCart() {
          this.$http.post(`/api/carts`).then(response => {
            this.message=response.data.message
          })
          .then(() => {
            this.getCarts();
            this.snackbar=true;
          })
        }
    },
    created() {
        this.getCarts();
    }
}
</script>