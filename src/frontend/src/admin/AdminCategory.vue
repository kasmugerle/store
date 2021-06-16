<template>
    <v-form 
    ref="form" 
    v-model="valid" 
    lazy-validation>
        <v-text-field 
        v-model="name" 
        :rules="nameRules" 
        label="Name of category" 
        required>
        </v-text-field>
        <v-btn
        :disabled="!valid"
        color="success"
        @click="submit()"
        >
        Submit
        </v-btn>
        <v-btn
        color="error"
        @click="reset()"
        >
        Reset
        </v-btn>
    </v-form>
</template>

<script>
  export default {
    data: () => ({
      valid: true,
      name: '',
      nameRules: [
        v => !!v || 'Category name is required'
      ],
    }),

    methods: {
      submit() {
        this.$refs.form.validate()
        this.$http.post("/admin/api/categories", {
            name: this.name
        }).then(() => {
            this.$router.push('/');
        })
      },
      reset () {
        this.$refs.form.reset()
      },
    },
  }
</script>