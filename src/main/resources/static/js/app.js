document.addEventListener("DOMContentLoaded", function () {

    /**
     * Form Select
     */
    class FormSelect {
        constructor($el) {
            this.$el = $el;
            this.options = [...$el.children];
            this.init();
        }

        init() {
            this.createElements();
            this.addEvents();
            this.$el.parentElement.removeChild(this.$el);
        }

        createElements() {
            // Input for value
            this.valueInput = document.createElement("input");
            this.valueInput.type = "text";
            this.valueInput.name = this.$el.name;

            // Dropdown container
            this.dropdown = document.createElement("div");
            this.dropdown.classList.add("dropdown");

            // List container
            this.ul = document.createElement("ul");

            // All list options
            this.options.forEach((el, i) => {
                const li = document.createElement("li");
                li.dataset.value = el.value;
                li.innerText = el.innerText;

                if (i === 0) {
                    // First clickable option
                    this.current = document.createElement("div");
                    this.current.innerText = el.innerText;
                    this.dropdown.appendChild(this.current);
                    this.valueInput.value = el.value;
                    li.classList.add("selected");
                }

                this.ul.appendChild(li);
            });

            this.dropdown.appendChild(this.ul);
            this.dropdown.appendChild(this.valueInput);
            this.$el.parentElement.appendChild(this.dropdown);
        }

        addEvents() {
            this.dropdown.addEventListener("click", e => {
                const target = e.target;
                this.dropdown.classList.toggle("selecting");

                // Save new value only when clicked on li
                if (target.tagName === "LI") {
                    this.valueInput.value = target.dataset.value;
                    this.current.innerText = target.innerText;
                }
            });
        }
    }

    document.querySelectorAll(".form-group--dropdown select").forEach(el => {
        new FormSelect(el);
    });

    /**
     * Hide elements when clicked on document
     */
    document.addEventListener("click", function (e) {
        const target = e.target;
        const tagName = target.tagName;

        if (target.classList.contains("dropdown")) return false;

        if (tagName === "LI" && target.parentElement.parentElement.classList.contains("dropdown")) {
            return false;
        }

        if (tagName === "DIV" && target.parentElement.classList.contains("dropdown")) {
            return false;
        }

        document.querySelectorAll(".form-group--dropdown .dropdown").forEach(el => {
            el.classList.remove("selecting");
        });
    });

    /**
     * Switching between form steps
     */
    class FormSteps {
        constructor(form) {
            this.$form = form;
            this.$next = form.querySelectorAll(".next-step");
            this.$prev = form.querySelectorAll(".prev-step");
            this.$step = form.querySelector(".form--steps-counter span");
            this.currentStep = 1;

            this.$stepInstructions = form.querySelectorAll(".form--steps-instructions p");
            const $stepForms = form.querySelectorAll("form > div");
            this.slides = [...this.$stepInstructions, ...$stepForms];

            this.init();
        }

        /**
         * Init all methods
         */
        init() {
            this.events();
            this.updateForm();
        }

        /**
         * All events that are happening in form
         */
        events() {
            // Next step
            this.$next.forEach(btn => {
                btn.addEventListener("click", e => {
                    e.preventDefault();
                    this.currentStep++;
                    this.updateForm();
                });
            });

            // Previous step
            this.$prev.forEach(btn => {
                btn.addEventListener("click", e => {
                    e.preventDefault();
                    this.currentStep--;
                    this.updateForm();
                });
            });

            // Form submit
            this.$form.querySelector("form").addEventListener("submit", e => this.submit(e));
        }

        /**
         * Update form front-end
         * Show next or previous section etc.
         */
        updateForm() {
            this.$step.innerText = this.currentStep;

            // TODO: Validation

            this.slides.forEach(slide => {
                slide.classList.remove("active");

                if (slide.dataset.step == this.currentStep) {
                    slide.classList.add("active");
                }
            })

            this.$stepInstructions[0].parentElement.parentElement.hidden = this.currentStep >= 5;
            this.$step.parentElement.hidden = this.currentStep >= 5;

            // TODO: get data from inputs and show them in summary

            if (this.currentStep == 5) {


                let quantity = document.querySelector("div[data-step='2']").querySelector("input");
                let organization = document.querySelector("input[type=radio][name=institution]:checked").parentElement.querySelector("div.title");
                let categories = document.querySelectorAll("input[type=checkbox][name=categories]:checked");
                let addressInputs = document.getElementById("addressData").querySelectorAll("input");
                let termInputs = document.getElementById("termData").querySelectorAll("input");
                let comment = document.getElementById("termData").querySelector("textarea");

                let addressData = document.getElementById('addressData-ul');
                let termData = document.getElementById('termData-ul');
                let bagsCategoriesData = document.getElementById('bagsData');
                let organizationData = document.getElementById('institutionData');


                console.log(categories);
                let bags = 'worków';
                let group1 = ['0', '1', '5', '6', '7', '8', '9'];
                let group2 = ['2', '3', '4'];

                if (quantity.value == 1) {
                    bags = ' worek';
                } else if ((quantity.value == 0 || quantity.value > 4 && quantity.value <= 21)) {
                    bags = ' worków';
                } else if (group1.includes(quantity.value.slice(-1))) {
                    bags = ' worków';
                } else if (group2.includes(quantity.value.slice(-1))) {
                    bags = ' worki';
                }

                bagsCategoriesData.innerText = quantity.value + bags + ' rzeczy z kategorii:';

                for (let i = 0; i < categories.length; i++) {
                    bagsCategoriesData.innerHTML += '<br> &nbsp; - ' + categories[i].parentElement.querySelector("span.description").innerText;
                }

                organizationData.innerText = 'Dla fundacji "' + organization.innerText + '" w Warszawie';

                addressData.innerText = '';
                termData.innerText = '';

                for (let i = 0; i < 4; i++) {
                    let newLi = document.createElement("li");
                    newLi.innerText = addressInputs[i].value;
                    addressData.appendChild(newLi);
                }

                for (let i = 0; i < 2; i++) {
                    let newLi = document.createElement("li");
                    newLi.innerText = termInputs[i].value;
                    termData.appendChild(newLi);
                }


                let newLi = document.createElement("li");
                if (comment.value !== "") {
                    newLi.innerText = comment.value;
                } else {
                    newLi.innerText = 'Brak uwag';
                }
                termData.appendChild(newLi);
            }

        }

    }

    const form = document.querySelector(".form--steps");
    if (form !== null) {
        new FormSteps(form);
    }
});
